package com.monorama.iot_server.dto.request.airquality;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.monorama.iot_server.domain.AirQualityData;
import com.monorama.iot_server.domain.User;
import com.monorama.iot_server.domain.embedded.AirQualityDataItem;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record AQDRequestDto(
        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        Date createdAt,

        @NotNull Double pm25Value,
        @NotNull Integer pm25Level,
        @NotNull Double pm10Value,
        @NotNull Integer pm10Level,
        @NotNull Double temperature,
        @NotNull Integer temperatureLevel,
        @NotNull Double humidity,
        @NotNull Integer humidityLevel,
        @NotNull Double co2Value,
        @NotNull Integer co2Level,
        @NotNull Double vocValue,
        @NotNull Integer vocLevel,
        @NotNull Double picoDeviceLatitude,
        @NotNull Double picoDeviceLongitude
) {
    public AirQualityData toEntity(User user) {
        AirQualityData entity = new AirQualityData();
        entity.setCreatedAt(this.createdAt);
        entity.setAirQualityDataItem(
                AirQualityDataItem.builder()
                        .pm25Value(pm25Value)
                        .pm25Level(pm25Level)
                        .pm10Value(pm10Value)
                        .pm10Level(pm10Level)
                        .temperature(temperature)
                        .temperatureLevel(temperatureLevel)
                        .humidity(humidity)
                        .humidityLevel(humidityLevel)
                        .co2Value(co2Value)
                        .co2Level(co2Level)
                        .vocValue(vocValue)
                        .vocLevel(vocLevel)
                        .picoDeviceLatitude(picoDeviceLatitude)
                        .picoDeviceLongitude(picoDeviceLongitude)
                        .build()
        );
        entity.setUser(user);
        return entity;
    }
}