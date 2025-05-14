package com.monorama.iot_server.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ERole {
    PM("PM", "ROLE_PM"),
    HEALTH_DATA_USER("HD_USER", "ROLE_HD_USER"),
    AIR_QUALITY_USER("AQ_USER", "ROLE_AQ_USER"),
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
