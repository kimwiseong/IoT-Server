package com.monorama.iot_server.dto.request.pm;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public record ProjectRequestDto(
        @NotNull String projectTitle,
        @NotNull Integer participant,
        @NotNull String description,
        @NotNull String projectType,

        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING, timezone = "Asia/Seoul")
        Date startDate,

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
        Date endDate,
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        Date createdAt,

        @NotNull Boolean email,
        @NotNull Boolean gender,
        @NotNull Boolean phoneNumber,
        @NotNull Boolean dateOfBirth,
        @NotNull Boolean bloodType,
        @NotNull Boolean weight,
        @NotNull Boolean height,
        @NotNull Boolean name,

        @NotNull Boolean stepCount,
        @NotNull Boolean runningSpeed,
        @NotNull Boolean basalEnergyBurned,
        @NotNull Boolean activeEnergyBurned,
        @NotNull Boolean sleepAnalysis,
        @NotNull Boolean heartRate,
        @NotNull Boolean oxygenSaturation,
        @NotNull Boolean bloodPressureSystolic,
        @NotNull Boolean bloodPressureDiastolic,
        @NotNull Boolean respiratoryRate,
        @NotNull Boolean bodyTemperature,
        @NotNull Boolean ecgData,
        @NotNull Boolean watchDeviceLatitude,
        @NotNull Boolean watchDeviceLongitude,

        @NotNull Boolean pm25Value,
        @NotNull Boolean pm25Level,
        @NotNull Boolean pm10Value,
        @NotNull Boolean pm10Level,
        @NotNull Boolean temperature,
        @NotNull Boolean temperatureLevel,
        @NotNull Boolean humidity,
        @NotNull Boolean humidityLevel,
        @NotNull Boolean co2Value,
        @NotNull Boolean co2Level,
        @NotNull Boolean vocValue,
        @NotNull Boolean vocLevel,
        @NotNull Boolean picoDeviceLatitude,
        @NotNull Boolean picoDeviceLongitude,

        List<AirMetaDataItemRequestDto> airMetaDataItemRequestDtoList
) {

}
