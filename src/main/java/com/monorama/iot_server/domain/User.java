package com.monorama.iot_server.domain;

import com.monorama.iot_server.domain.embedded.PersonalInfoItem;
import com.monorama.iot_server.type.EProvider;
import com.monorama.iot_server.type.ERole;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;


@Entity
@NoArgsConstructor
@Getter
@Table(name = "user_tb")
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private final Long id = 0L;

    @Embedded
    private PersonalInfoItem personalInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ERole role;

    @Column(name = "social_id")
    private String socialId;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider")
    private EProvider provider;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "is_login")
    private Boolean isLogin;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(mappedBy = "user")
    private List<HealthData> healthDataList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<AirQualityData> airQualityDataList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Project> projectList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<AirMetaData> airMetaDataList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserProject> userProjectList = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "user_data_permission_id")
    private UserDataPermission userDataPermission;

    @Builder
    public User(ERole role, String socialId, EProvider provider) {
        this.role = role;
        this.socialId = socialId;
        this.provider = provider;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void register(PersonalInfoItem personalInfo) {
        this.personalInfo = personalInfo;
    }

    private void setUserDataPermission(UserDataPermission userDataPermission) {
        this.userDataPermission = userDataPermission;
    }
}




