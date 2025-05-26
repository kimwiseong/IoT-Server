package com.monorama.iot_server.service;

import com.monorama.iot_server.domain.embedded.AirQualityDataFlag;
import com.monorama.iot_server.domain.embedded.HealthDataFlag;
import com.monorama.iot_server.domain.embedded.PersonalInfoFlag;
import com.monorama.iot_server.repository.ProjectRepository;
import com.monorama.iot_server.repository.UserDataPermissionRepository;
import com.monorama.iot_server.repository.UserProjectRepository;
import com.monorama.iot_server.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionUpdateService {

    private final UserDataPermissionRepository userDataPermissionRepository;
    private final UserProjectRepository userProjectRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void refreshUserPermission() {

        Date today = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        userDataPermissionRepository.findAll().forEach(userDataPermission -> {

            PersonalInfoFlag personalInfoFlag = new PersonalInfoFlag();
            AirQualityDataFlag airQualityDataFlag = new AirQualityDataFlag();
            HealthDataFlag healthDataFlag = new HealthDataFlag();

            userProjectRepository.findProgressProjectsByUserId(
                    userDataPermission.getUser().getId(),
                    today
            ).forEach(project -> {
                personalInfoFlag.updateBy(project.getPersonalInfoFlag());
                airQualityDataFlag.updateBy(project.getAirQualityDataFlag());
                healthDataFlag.updateBy(project.getHealthDataFlag());
            });

            userDataPermission.getAirQualityDataFlag().updateBy(airQualityDataFlag);
            userDataPermission.getHealthDataFlag().updateBy(healthDataFlag);
            userDataPermission.getPersonalInfoFlag().updateBy(personalInfoFlag);
        } );
    }

}
