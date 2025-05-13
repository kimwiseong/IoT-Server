package com.monorama.iot_server.dto.response.project;

import com.monorama.iot_server.domain.Project;
import com.monorama.iot_server.domain.embedded.AirQualityDataFlag;
import com.monorama.iot_server.domain.embedded.HealthDataFlag;
import com.monorama.iot_server.domain.embedded.PersonalInfoFlag;

import java.time.LocalDate;
import java.util.Date;

public record ProjectDetailResponseDto(
        String projectTitle,
        int participant,
        String description,
        Date startDate,
        Date endDate,
        Date createdDate,

        boolean email,
        boolean gender,
        boolean phoneNumber,
        boolean dateOfBirth,
        boolean bloodType,
        boolean height,
        boolean weight,
        boolean name,

        boolean stepCount,
        boolean runningSpeed,
        boolean basalEnergyBurned,
        boolean activeEnergyBurned,
        boolean sleepAnalysis,
        boolean heartRate,
        boolean oxygenSaturation,
        boolean bloodPressureSystolic,
        boolean bloodPressureDiastolic,
        boolean respiratoryRate,
        boolean bodyTemperature,
        boolean ecgData,
        boolean watchDeviceLatitude,
        boolean watchDeviceLongitude,

        boolean pm25Value,
        boolean pm25Level,
        boolean pm10Value,
        boolean pm10Level,
        boolean temperature,
        boolean temperatureLevel,
        boolean humidity,
        boolean humidityLevel,
        boolean co2Value,
        boolean co2Level,
        boolean vocValue,
        boolean vocLevel,
        boolean picoDeviceLatitude,
        boolean picoDeviceLongitude
) {
    public static ProjectDetailResponseDto fromEntity(Project project) {
        PersonalInfoFlag p = project.getPersonalInfoFlag();
        HealthDataFlag h = project.getHealthDataFlag();
        AirQualityDataFlag a = project.getAirQualityDataFlag();

        return new ProjectDetailResponseDto(
                project.getTitle(),
                project.getParticipant(),
                project.getDescription(),
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
