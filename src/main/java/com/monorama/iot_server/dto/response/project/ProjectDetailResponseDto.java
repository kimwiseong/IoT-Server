package com.monorama.iot_server.dto.response.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.monorama.iot_server.domain.Project;
import com.monorama.iot_server.domain.embedded.AirQualityDataFlag;
import com.monorama.iot_server.domain.embedded.HealthDataFlag;
import com.monorama.iot_server.domain.embedded.PersonalInfoFlag;

import java.util.Date;

public record ProjectDetailResponseDto(
        String projectTitle,
        Integer participant,
        String description,
        String projectType,

        @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
        Date startDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        Date endDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        Date createdAt,

        Boolean email,
        Boolean gender,
        Boolean phoneNumber,
        Boolean dateOfBirth,
        Boolean bloodType,
        Boolean height,
        Boolean weight,
        Boolean name,

        Boolean stepCount,
        Boolean runningSpeed,
        Boolean basalEnergyBurned,
        Boolean activeEnergyBurned,
        Boolean sleepAnalysis,
        Boolean heartRate,
        Boolean oxygenSaturation,
        Boolean bloodPressureSystolic,
        Boolean bloodPressureDiastolic,
        Boolean respiratoryRate,
        Boolean bodyTemperature,
        Boolean ecgData,
        Boolean watchDeviceLatitude,
        Boolean watchDeviceLongitude,

        Boolean pm25Value,
        Boolean pm25Level,
        Boolean pm10Value,
        Boolean pm10Level,
        Boolean temperature,
        Boolean temperatureLevel,
        Boolean humidity,
        Boolean humidityLevel,
        Boolean co2Value,
        Boolean co2Level,
        Boolean vocValue,
        Boolean vocLevel,
        Boolean picoDeviceLatitude,
        Boolean picoDeviceLongitude
) {
    public static ProjectDetailResponseDto fromEntity(Project project) {
        PersonalInfoFlag p = project.getPersonalInfoFlag();
        HealthDataFlag h = project.getHealthDataFlag();
        AirQualityDataFlag a = project.getAirQualityDataFlag();

        return new ProjectDetailResponseDto(
                project.getTitle(),
                project.getParticipant(),
                project.getDescription(),
                project.getProjectType().toString(),
                project.getStartDate(),
                project.getEndDate(),
                project.getCreatedAt(),

                p.getEmail(),
                p.getGender(),
                p.getPhoneNumber(),
                p.getDateOfBirth(),
                p.getBloodType(),
                p.getHeight(),
                p.getWeight(),
                p.getName(),

                h.getStepCount(),
                h.getRunningSpeed(),
                h.getBasalEnergyBurned(),
                h.getActiveEnergyBurned(),
                h.getSleepAnalysis(),
                h.getHeartRate(),
                h.getOxygenSaturation(),
                h.getBloodPressureSystolic(),
                h.getBloodPressureDiastolic(),
                h.getRespiratoryRate(),
                h.getBodyTemperature(),
                h.getEcgData(),
                h.getWatchDeviceLatitude(),
                h.getWatchDeviceLongitude(),

                a.getPm25Value(),
                a.getPm25Level(),
                a.getPm10Value(),
                a.getPm10Level(),
                a.getTemperature(),
                a.getTemperatureLevel(),
                a.getHumidity(),
                a.getHumidityLevel(),
                a.getCo2Value(),
                a.getCo2Level(),
                a.getVocValue(),
                a.getVocLevel(),
                a.getPicoDeviceLatitude(),
                a.getPicoDeviceLongitude()
        );
    }
}
