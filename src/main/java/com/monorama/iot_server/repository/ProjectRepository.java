package com.monorama.iot_server.repository;

import com.monorama.iot_server.domain.Project;
import com.monorama.iot_server.domain.type.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByProjectType(ProjectType projectType);
}

