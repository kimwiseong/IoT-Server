package com.monorama.iot_server.dto.request.AirQuality;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.monorama.iot_server.domain.AirQualityData;
import com.monorama.iot_server.domain.User;
import com.monorama.iot_server.domain.embedded.AirQualityDataItem;

import java.util.Date;

public record AirQualityDataRequestDto(
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        Date createAt,
        Double pm25Value,
        Integer pm25Level,
        Double pm10Value,
        Integer pm10Level,
        Double temperature,
        Integer temperatureLevel,
        Double humidity,
        Integer humidityLevel,
        Double co2Value,
        Integer co2Level,
        Double vocValue,
        Integer vocLevel,
        Double picoDeviceLatitude,
        Double picoDeviceLongitude
) {
    public AirQualityData toEntity(User user) {
        AirQualityData entity = new AirQualityData();
        entity.setCreatedAt(this.createAt);
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