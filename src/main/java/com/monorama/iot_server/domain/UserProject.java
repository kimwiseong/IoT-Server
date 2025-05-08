package com.monorama.iot_server.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Entity(name = "user_project_tb")
public class UserProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_project_id")
    private final Long id = 0L;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    private void setUser(User user) {
        this.user = user;
        user.getUserProjectList().add(this);
    }

    private void setProject(Project project) {
        this.project = project;
        project.getUserProjectList().add(this);
    }


}
