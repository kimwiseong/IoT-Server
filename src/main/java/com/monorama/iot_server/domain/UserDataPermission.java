package com.monorama.iot_server.domain;

import com.monorama.iot_server.domain.embedded.AirQualityDataFlag;
import com.monorama.iot_server.domain.embedded.HealthDataFlag;
import com.monorama.iot_server.domain.embedded.PersonalInfoFlag;
import jakarta.persistence.*;

@Entity(name = "user_data_permission_tb")
public class UserDataPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_data_permission_id")
    private final Long id = 0L;

    @Embedded
    private PersonalInfoFlag personalInfoFlag;

    @Embedded
    private HealthDataFlag healthDataFlag;

    @Embedded
    private AirQualityDataFlag airQualityDataFlag;
}
