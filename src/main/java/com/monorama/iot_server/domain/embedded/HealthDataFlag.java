package com.monorama.iot_server.domain.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class HealthDataFlag {

    @Column(name = "step_count_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean stepCount;

    @Column(name = "running_speed_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean runningSpeed;

    @Column(name = "basal_energy_burned_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean basalEnergyBurned;

    @Column(name = "active_energy_burned_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean activeEnergyBurned;

    @Column(name = "sleep_analysis_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean sleepAnalysis;

    @Column(name = "heart_rate_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean heartRate;

    @Column(name = "oxygen_saturation_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean oxygenSaturation;

    @Column(name = "blood_pressure_systolic_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean bloodPressureSystolic;

    @Column(name = "blood_pressure_diastolic_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean bloodPressureDiastolic;

    @Column(name = "respiratory_rate_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean respiratoryRate;

    @Column(name = "body_temperature_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean bodyTemperature;

    @Column(name = "ecg_data_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean ecgData;

    @Column(name = "watch_device_latitude_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean watchDeviceLatitude;

    @Column(name = "watch_device_longitude_flag", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean watchDeviceLongitude;

    public void updateBy(HealthDataFlag projectFlag) {
        if (projectFlag.stepCount) this.stepCount = true;
        if (projectFlag.runningSpeed) this.runningSpeed = true;
        if (projectFlag.basalEnergyBurned) this.basalEnergyBurned = true;
        if (projectFlag.activeEnergyBurned) this.activeEnergyBurned = true;
        if (projectFlag.sleepAnalysis) this.sleepAnalysis = true;
        if (projectFlag.heartRate) this.heartRate = true;
        if (projectFlag.oxygenSaturation) this.oxygenSaturation = true;
        if (projectFlag.bloodPressureSystolic) this.bloodPressureSystolic = true;
        if (projectFlag.bloodPressureDiastolic) this.bloodPressureDiastolic = true;
        if (projectFlag.respiratoryRate) this.respiratoryRate = true;
        if (projectFlag.bodyTemperature) this.bodyTemperature = true;
        if (projectFlag.ecgData) this.ecgData = true;
        if (projectFlag.watchDeviceLatitude) this.watchDeviceLatitude = true;
        if (projectFlag.watchDeviceLongitude) this.watchDeviceLongitude = true;
    }
}
