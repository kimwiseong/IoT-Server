package com.monorama.iot_server.domain.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AirQualityDataItem {

    @Column(name = "pm25_value")
    private Double pm25Value;

    @Column(name = "pm25_level")
    private Integer pm25Level;

    @Column(name = "pm10_value")
    private Double pm10Value;

    @Column(name = "pm10_level")
    private Integer pm10Level;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "temperature_level")
    private Integer temperatureLevel;

    @Column(name = "humidity")
    private Double humidity;

    @Column(name = "humidity_level")
    private Integer humidityLevel;

    @Column(name = "co2_value")
    private Double co2Value;

    @Column(name = "co2_level")
    private Integer co2Level;

    @Column(name = "voc_value")
    private Double vocValue;

    @Column(name = "voc_level")
    private Integer vocLevel;

    @Column(name = "pico_device_latitude")
    private Double picoDeviceLatitude;

    @Column(name = "pico_device_longitude")
    private Double picoDeviceLongitude;
}

