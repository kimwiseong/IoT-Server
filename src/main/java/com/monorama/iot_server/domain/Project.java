package com.monorama.iot_server.domain;

import com.monorama.iot_server.domain.embedded.AirQualityDataFlag;
import com.monorama.iot_server.domain.embedded.HealthDataFlag;
import com.monorama.iot_server.domain.embedded.PersonalInfoFlag;
import com.monorama.iot_server.domain.type.ProjectType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "project_tb")
@NoArgsConstructor
@Getter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_type")
    private ProjectType projectType;

    @Column(name = "title")
    private String title;

    @Column(name = "participant")
    private Integer participant;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "description")
    private String description;

    @Column(name = "terms_of_policy")
    private String termsOfPolicy;

    @Column(name = "privacy_policy")
    private String privacyPolicy;

    @Column(name = "health_data_consent")
    private String healthDataConsent;

    @Column(name = "air_data_consent")
    private String airDataConsent;

    @Column(name = "local_data_terms_of_service")
    private String localDataTermsOfService;

    @Embedded
    private PersonalInfoFlag personalInfoFlag = new PersonalInfoFlag();

    @Embedded
    private HealthDataFlag healthDataFlag = new HealthDataFlag();

    @Embedded
    private AirQualityDataFlag airQualityDataFlag = new AirQualityDataFlag();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "project")
    private List<UserProject> userProjectList = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<AirMetaDataItem> airMetaDataItemList = new ArrayList<>();

    @OneToMany(mappedBy = "project")
    private List<AirMetaData> airMetaDataList = new ArrayList<>();

    @Builder
    public Project(ProjectType projectType, String title, Integer participant, Date startDate, Date endDate, String description, String termsOfPolicy, String privacyPolicy, String healthDataConsent, String airDataConsent, String localDataTermsOfService) {
        this.projectType = projectType;
        this.title = title;
        this.participant = participant;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.termsOfPolicy = termsOfPolicy;
        this.privacyPolicy = privacyPolicy;
        this.healthDataConsent = healthDataConsent;
        this.airDataConsent = airDataConsent;
        this.localDataTermsOfService = localDataTermsOfService;
    }

    private void setUser(User user) {
        this.user = user;
        user.getProjectList().add(this);
    }
}
