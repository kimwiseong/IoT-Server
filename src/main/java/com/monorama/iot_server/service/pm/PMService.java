package com.monorama.iot_server.service.pm;

import com.monorama.iot_server.dto.response.project.ProjectListForPMResponseDto;
import com.monorama.iot_server.dto.response.project.ProjectSimpleForPMResponseDto;
import com.monorama.iot_server.repository.ProjectRepository;
import com.monorama.iot_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PMService {
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public ProjectListForPMResponseDto getProjectList(Long pmId) {
        List<ProjectSimpleForPMResponseDto> projects = projectRepository.findAllByPMId(pmId)
                .stream()
                .map(ProjectSimpleForPMResponseDto::fromEntity)
                .toList();
        return ProjectListForPMResponseDto.of(projects);
    }
}
