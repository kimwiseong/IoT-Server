package com.monorama.iot_server.domain.embedded;

import com.monorama.iot_server.domain.type.ProjectType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Embeddable
@Getter
@NoArgsConstructor
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

    /*** constructor ***/
    @Builder
    public AirQualityDataItem(Double pm25Value, Integer pm25Level, Double pm10Value, Integer pm10Level, Double temperature, Integer temperatureLevel, Double humidity, Integer humidityLevel, Double co2Value, Integer co2Level, Double vocValue, Integer vocLevel, Double picoDeviceLatitude, Double picoDeviceLongitude) {
        this.pm25Value = pm25Value;
        this.pm25Level = pm25Level;
        this.pm10Value = pm10Value;
        this.pm10Level = pm10Level;
        this.temperature = temperature;
        this.temperatureLevel = temperatureLevel;
        this.humidity = humidity;
        this.humidityLevel = humidityLevel;
        this.co2Value = co2Value;
        this.co2Level = co2Level;
        this.vocValue = vocValue;
        this.vocLevel = vocLevel;
        this.picoDeviceLatitude = picoDeviceLatitude;
        this.picoDeviceLongitude = picoDeviceLongitude;
    }
}

