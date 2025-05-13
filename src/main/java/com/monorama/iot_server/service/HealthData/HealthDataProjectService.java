package com.monorama.iot_server.service.HealthData;

import com.monorama.iot_server.domain.Project;
import com.monorama.iot_server.domain.type.ProjectType;
import com.monorama.iot_server.domain.type.TermsType;
import com.monorama.iot_server.dto.response.project.ProjectDetailResponseDto;
import com.monorama.iot_server.dto.response.project.ProjectListResponseDto;
import com.monorama.iot_server.dto.response.project.ProjectSimpleResponseDto;
import com.monorama.iot_server.dto.response.terms.TermsContentResponseDto;
import com.monorama.iot_server.exception.CommonException;
import com.monorama.iot_server.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.monorama.iot_server.exception.ErrorCode;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthDataProjectService {

    private final ProjectRepository projectRepository;

    public ProjectListResponseDto getAllHealthProjects() {
        List<ProjectSimpleResponseDto> projects = projectRepository.findByProjectType(ProjectType.HEALTH_DATA)
                .stream()
                .map(ProjectSimpleResponseDto::fromEntity)
                .toList();

        return ProjectListResponseDto.of(projects);
    }

    public ProjectDetailResponseDto getProjectDetail(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_PROJECT));
        return ProjectDetailResponseDto.fromEntity(project);
    }

    public TermsContentResponseDto getTermsContent(Long projectId, TermsType type) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_RESOURCE));

        return TermsContentResponseDto.fromEntity(project, type);
    }
}