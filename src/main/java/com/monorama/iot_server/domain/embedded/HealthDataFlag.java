package com.monorama.iot_server.domain.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HealthDataFlag {

    @Column(name = "step_count")
    private Boolean stepCount;

    @Column(name = "running_speed")
    private Boolean runningSpeed;

    @Column(name = "basal_energy_burned")
    private Boolean basalEnergyBurned;

    @Column(name = "active_energy_burned")
    private Boolean activeEnergyBurned;

    @Column(name = "sleep_analysis")
    private Boolean sleepAnalysis;

    @Column(name = "heart_rate")
    private Boolean heartRate;

    @Column(name = "oxygen_saturation")
    private Boolean oxygenSaturation;

    @Column(name = "blood_pressure_systolic")
    private Boolean bloodPressureSystolic;

    @Column(name = "blood_pressure_diastolic")
    private Boolean bloodPressureDiastolic;

    @Column(name = "respiratory_rate")
    private Boolean respiratoryRate;

    @Column(name = "body_temperature")
    private Boolean bodyTemperature;

    @Column(name = "ecg_data")
    private Boolean ecgData;

    @Column(name = "watch_device_latitude")
    private Boolean watchDeviceLatitude;

    @Column(name = "watch_device_longitude")
    private Boolean watchDeviceLongitude;
}
