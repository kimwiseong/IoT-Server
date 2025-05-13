package com.monorama.iot_server.type;

public class EProviderFactory {
    public static EProvider of(String provider) {
        return switch (provider) {
            case "GOOGLE" -> EProvider.GOOGLE;
            default -> throw new IllegalArgumentException("Invalid Provider Type.");
        };
    }
}
