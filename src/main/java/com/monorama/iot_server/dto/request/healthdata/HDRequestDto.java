package com.monorama.iot_server.dto.request.healthdata;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.monorama.iot_server.domain.HealthData;
import com.monorama.iot_server.domain.User;
import com.monorama.iot_server.domain.embedded.HealthDataItem;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record HDRequestDto(
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        Date createdAt,

        Double stepCount,
        Double runningSpeed,
        Double basalEnergyBurned,
        Double activeEnergyBurned,
        String sleepAnalysis,
        Double heartRate,
        Double oxygenSaturation,
        Double bloodPressureSystolic,
        Double bloodPressureDiastolic,
        Double respiratoryRate,
        Double bodyTemperature,
        String ecgData,
        Double watchDeviceLatitude,
        Double watchDeviceLongitude
) {
    public HealthData toEntity(User user) {
        HealthDataItem item = HealthDataItem.builder()
                .stepCount(stepCount)
                .runningSpeed(runningSpeed)
                .basalEnergyBurned(basalEnergyBurned)
                .activeEnergyBurned(activeEnergyBurned)
                .sleepAnalysis(sleepAnalysis)
                .heartRate(heartRate)
                .oxygenSaturation(oxygenSaturation)
                .bloodPressureSystolic(bloodPressureSystolic)
                .bloodPressureDiastolic(bloodPressureDiastolic)
                .respiratoryRate(respiratoryRate)
                .bodyTemperature(bodyTemperature)
                .ecgData(ecgData)
                .watchDeviceLatitude(watchDeviceLatitude)
                .watchDeviceLongitude(watchDeviceLongitude)
                .build();

        return HealthData.builder()
                .createdAt(createdAt)
                .user(user)
                .healthDataItem(item)
                .build();
    }
}
