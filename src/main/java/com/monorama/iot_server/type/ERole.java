package com.monorama.iot_server.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ERole {
    PM("PM", "ROLE_PM"),
    HEALTH_DATA_USER("HEALTH_DATA_USER", "ROLE_HEALTH_DATA_USER"),
    AIR_QUALITY_USER("AIR_QUALITY_USER", "ROLE_AIR_QUALITY_USER"),
    BOTH_USER("BOTH_USER", "ROLE_BOTH_USER"),
    ADMIN("ADMIN", "ROLE_ADMIN"),
    GUEST("GUEST", "ROLE_GUEST");

    private final String name;
    private final String securityName;

    @Override
    public String toString() {
        return this.name;
    }

    public String toSecurityString() {
        return this.securityName;
    }

}
