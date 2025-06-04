package com.monorama.iot_server.service.healthdata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monorama.iot_server.domain.Project;
import com.monorama.iot_server.domain.User;
import com.monorama.iot_server.domain.UserDataPermission;
import com.monorama.iot_server.domain.UserProject;
import com.monorama.iot_server.domain.type.TermsType;
import com.monorama.iot_server.dto.response.project.AirMetaDataItemResponseDto;
import com.monorama.iot_server.dto.response.project.ProjectDetailResponseDto;
import com.monorama.iot_server.dto.response.project.ProjectListResponseDto;
import com.monorama.iot_server.dto.response.project.ProjectSimpleResponseDto;
import com.monorama.iot_server.dto.response.terms.TermsContentResponseDto;
import com.monorama.iot_server.exception.CommonException;
import com.monorama.iot_server.repository.AirMetaDataItemRepository;
import com.monorama.iot_server.repository.ProjectRepository;
import com.monorama.iot_server.repository.UserProjectRepository;
import com.monorama.iot_server.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import com.monorama.iot_server.exception.ErrorCode;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class HDProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final UserProjectRepository userProjectRepository;
    private final AirMetaDataItemRepository airMetaDataItemRepository;

    @Value("${external.elasticsearch.url}")
    private String esApiUrl;

    @Value("${external.elasticsearch.username}")
    private String esUsername;

    @Value("${external.elasticsearch.password}")
    private String esPassword;

    public ProjectListResponseDto getAvailableHealthProjectList() {
        List<ProjectSimpleResponseDto> projects = projectRepository.findActiveHealthProjects()
                .stream()
                .map(ProjectSimpleResponseDto::fromEntity)
                .toList();

        return ProjectListResponseDto.of(projects);
    }

    public ProjectDetailResponseDto getProjectDetail(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PROJECT));

        List<AirMetaDataItemResponseDto> airMetaDataItemDtoList = airMetaDataItemRepository.findAllByProjectId(project.getId())
                .stream()
                .map(AirMetaDataItemResponseDto::fromEntity)
                .toList();

        return ProjectDetailResponseDto.fromEntity(project, airMetaDataItemDtoList);
    }

    public TermsContentResponseDto getTermsContent(Long projectId, TermsType type) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        return TermsContentResponseDto.fromEntity(project, type);
    }

    @Transactional
    public void participateProject(Long userId, Long projectId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        Project project = projectRepository.findActiveHealthProjectById(projectId)
                .orElseThrow(() -> new CommonException(ErrorCode.PROJECT_NOT_AVAILABLE));

        if (userProjectRepository.existsByUserAndProject(user, project)) {
            throw new CommonException(ErrorCode.ALREADY_JOINED_PROJECT);
        }

        userProjectRepository.save(
                UserProject.builder()
                        .user(user)
                        .project(project)
                        .build()
        );

        UserDataPermission permission = user.getUserDataPermission();
        permission.getAirQualityDataFlag().updateBy(project.getAirQualityDataFlag());
        permission.getHealthDataFlag().updateBy(project.getHealthDataFlag());
        permission.getPersonalInfoFlag().updateBy(project.getPersonalInfoFlag());

        addProjectToKibanaUser(userId, projectId);
    }

    @org.springframework.transaction.annotation.Transactional
    public void addProjectToKibanaUser(Long userId, Long projectId) {
        String userName = "user" + userId;
        Map<String, Object> userMetadata = fetchUserMetadata(userName);

        List<String> projectIds = (List<String>) userMetadata.getOrDefault("projectIds", new ArrayList<>());
        String newProjectId = String.valueOf(projectId);

        if (!projectIds.contains(newProjectId)) {
            projectIds.add(newProjectId);
        }

        userMetadata.put("projectIds", projectIds);
        userMetadata.put("userId", userId);

        updateUserMetadata(userName, userMetadata);
    }

    private Map<String, Object> fetchUserMetadata(String userName) {
        String url = esApiUrl + "/_security/user/" + userName;
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(esUsername, esPassword);

        ResponseEntity<JsonNode> response = new RestTemplate().exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                JsonNode.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new CommonException(ErrorCode.EXTERNAL_API_ERROR);
        }

        JsonNode metadataNode = response.getBody().path(userName).path("metadata");
        return new ObjectMapper().convertValue(metadataNode, new TypeReference<>() {});
    }

    private void updateUserMetadata(String userName, Map<String, Object> metadata) {
        String url = esApiUrl + "/_security/user/" + userName;

        // 기존 사용자 정보 조회 (role 유지)
        Map<String, Object> existingUser = fetchUser(userName);
        List<String> roles = (List<String>) existingUser.get("roles");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("metadata", metadata);
        requestBody.put("roles", roles);  // 필수

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(esUsername, esPassword);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = new RestTemplate().exchange(
                url,
                HttpMethod.PUT,
                request,
                String.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new CommonException(ErrorCode.EXTERNAL_API_ERROR);
        }
    }

    private Map<String, Object> fetchUser(String userName) {
        String url = esApiUrl + "/_security/user/" + userName;
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(esUsername, esPassword);

        ResponseEntity<JsonNode> response = new RestTemplate().exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                JsonNode.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new CommonException(ErrorCode.EXTERNAL_API_ERROR);
        }

        return new ObjectMapper().convertValue(response.getBody().path(userName), new TypeReference<>() {});
    }
}