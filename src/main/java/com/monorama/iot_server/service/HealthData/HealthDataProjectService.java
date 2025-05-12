package com.monorama.iot_server.service.HealthData;

import com.monorama.iot_server.domain.type.ProjectType;
import com.monorama.iot_server.dto.response.project.ProjectListResponseDto;
import com.monorama.iot_server.dto.response.project.ProjectSimpleResponseDto;
import com.monorama.iot_server.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}

