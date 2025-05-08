package com.monorama.iot_server.domain.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LoginProvider {
    SAMSUNG("SAMSUNG"),
    APPLE("APPLE"),
    GOOGLE("GOOGLE");

    private final String provider;

    @Override
    public String toString() {
        return provider;
    }
}
