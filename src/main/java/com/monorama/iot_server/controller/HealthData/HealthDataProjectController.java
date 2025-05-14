package com.monorama.iot_server.controller.HealthData;

import com.monorama.iot_server.annotation.UserId;
import com.monorama.iot_server.dto.ResponseDto;
import com.monorama.iot_server.dto.response.project.ProjectDetailResponseDto;
import com.monorama.iot_server.dto.response.project.ProjectListResponseDto;
import com.monorama.iot_server.service.HealthData.HealthDataProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/health-data")
@RequiredArgsConstructor
public class HealthDataProjectController {

    private final HealthDataProjectService projectService;

    @GetMapping("/projects")
    public ResponseDto<ProjectListResponseDto> getAllProjects() {
        return ResponseDto.ok(projectService.getAvailableHealthProjectList());
    }

    @GetMapping("/projects/{projectId}")
    public ResponseDto<ProjectDetailResponseDto> getProjectDetail(@PathVariable Long projectId) {
        return ResponseDto.ok(projectService.getProjectDetail(projectId));
    }

    @PostMapping("/projects/{projectId}/participation")
    public ResponseDto<?> participateProject(
            @UserId Long userId,
            @PathVariable Long projectId) {
        projectService.participateProject(userId, projectId);
        return ResponseDto.ok(null);
    }

}

