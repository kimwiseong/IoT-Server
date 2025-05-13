package com.monorama.iot_server.domain.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class AirQualityDataFlag {

    @Column(name = "pm25_value_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean pm25Value;

    @Column(name = "pm25_level_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean pm25Level;

    @Column(name = "pm10_value_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean pm10Value;

    @Column(name = "pm10_level_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean pm10Level;

    @Column(name = "temperature_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean temperature;

    @Column(name = "temperature_level_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean temperatureLevel;

    @Column(name = "humidity_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean humidity;

    @Column(name = "humidity_level_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean humidityLevel;

    @Column(name = "co2_value_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean co2Value;

    @Column(name = "co2_level_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean co2Level;

    @Column(name = "voc_value_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean vocValue;

    @Column(name = "voc_level_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean vocLevel;

    @Column(name = "pico_device_latitude_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean picoDeviceLatitude;

    @Column(name = "pico_device_longitude_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean picoDeviceLongitude;
}
