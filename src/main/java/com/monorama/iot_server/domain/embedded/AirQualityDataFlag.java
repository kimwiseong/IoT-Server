package com.monorama.iot_server.domain.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AirQualityDataFlag {

    @Column(name = "pm25_value")
    private Boolean pm25Value;

    @Column(name = "pm25_level")
    private Boolean pm25Level;

    @Column(name = "pm10_value")
    private Boolean pm10Value;

    @Column(name = "pm10_level")
    private Boolean pm10Level;

    @Column(name = "temperature")
    private Boolean temperature;

    @Column(name = "temperature_level")
    private Boolean temperatureLevel;

    @Column(name = "humidity")
    private Boolean humidity;

    @Column(name = "humidity_level")
    private Boolean humidityLevel;

    @Column(name = "co2_value")
    private Boolean co2Value;

    @Column(name = "co2_level")
    private Boolean co2Level;

    @Column(name = "voc_value")
    private Boolean vocValue;

    @Column(name = "voc_level")
    private Boolean vocLevel;

    @Column(name = "pico_device_latitude")
    private Boolean picoDeviceLatitude;

    @Column(name = "pico_device_longitude")
    private Boolean picoDeviceLongitude;
}
