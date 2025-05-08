package com.monorama.iot_server.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public enum Role {
    PM("PM"),
    HEALTH_DATA_USER("HEALTH_DATA_USER"),
    AIR_QUALITY_USER("AIR_QUALITY_USER"),
    BOTH_USER("BOTH_USER");

    private final String role;

    @Override
    public String toString() {
        return role;
    }
}
