package com.monorama.iot_server.domain;

import com.monorama.iot_server.domain.embedded.AirQualityDataFlag;
import com.monorama.iot_server.domain.embedded.HealthDataFlag;
import com.monorama.iot_server.domain.embedded.PersonalInfoFlag;
import jakarta.persistence.*;

@Entity
@Table(name = "user_data_permission_tb")
public class UserDataPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_data_permission_id", updatable = false)
    private Long id;

    /*** basic information ***/
    @Embedded
    private PersonalInfoFlag personalInfoFlag;

    @Embedded
    private HealthDataFlag healthDataFlag;

    @Embedded
    private AirQualityDataFlag airQualityDataFlag;
}
