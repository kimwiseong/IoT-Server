package com.monorama.iot_server.dto.response.project;

import com.monorama.iot_server.domain.Project;

public record ProjectSimpleResponseDto(
        Long projectId,
        String projectTitle
) {
    public static ProjectSimpleResponseDto fromEntity(Project project) {
        return new ProjectSimpleResponseDto(project.getId(), project.getTitle());
    }
}
