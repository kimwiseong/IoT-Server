package com.monorama.iot_server.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user_project_tb")
public class UserProject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_project_id", updatable = false)
    private Long id;

    /*** basic information ***/
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    /*** mapping information ***/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    /*** business logic ***/
    private void setUser(User user) {
        this.user = user;
        user.getUserProjectList().add(this);
    }

    private void setProject(Project project) {
        this.project = project;
        project.getUserProjectList().add(this);
    }
}
