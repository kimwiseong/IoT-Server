package com.monorama.iot_server.controller.HealthData;

import com.monorama.iot_server.dto.ResponseDto;
import com.monorama.iot_server.dto.response.project.ProjectDetailResponseDto;
import com.monorama.iot_server.dto.response.project.ProjectListResponseDto;
import com.monorama.iot_server.service.HealthData.HealthDataProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health-data")
@RequiredArgsConstructor
public class HealthDataProjectController {

    private final HealthDataProjectService projectService;

    @GetMapping("/projects")
    public ResponseDto<ProjectListResponseDto> getAllProjects() {
        return ResponseDto.ok(projectService.getAllHealthProjects());
    }

    @GetMapping("/projects/{projectId}")
    public ResponseDto<ProjectDetailResponseDto> getProjectDetail(@PathVariable Long projectId) {
        return ResponseDto.ok(projectService.getProjectDetail(projectId));
    }
}

