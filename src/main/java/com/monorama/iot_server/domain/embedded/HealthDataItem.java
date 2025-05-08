package com.monorama.iot_server.domain.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HealthDataItem {

    @Column(name = "step_count")
    private Double stepCount;

    @Column(name = "running_speed")
    private Double runningSpeed;

    @Column(name = "basal_energy_burned")
    private Double basalEnergyBurned;

    @Column(name = "active_energy_burned")
    private Double activeEnergyBurned;

    @Column(name = "sleep_analysis")
    private String sleepAnalysis;

    @Column(name = "heart_rate")
    private Double heartRate;

    @Column(name = "oxygen_saturation")
    private Double oxygenSaturation;

    @Column(name = "blood_pressure_systolic")
    private Double bloodPressureSystolic;

    @Column(name = "blood_pressure_diastolic")
    private Double bloodPressureDiastolic;

    @Column(name = "respiratory_rate")
    private Double respiratoryRate;

    @Column(name = "body_temperature")
    private Double bodyTemperature;

    @Column(name = "ecg_data")
    private String ecgData;

    @Column(name = "watch_device_latitude")
    private Double watchDeviceLatitude;

    @Column(name = "watch_device_longitude")
    private Double watchDeviceLongitude;
}

