package com.monorama.iot_server.type;

import static com.monorama.iot_server.type.EProvider.KAKAO;

public class EProviderFactory {
    public static EProvider of(String provider) {
        return switch (provider) {
            case "KAKAO" -> KAKAO;
            case "GOOGLE" -> EProvider.GOOGLE;
            case "NAVER" -> EProvider.NAVER;
            case "GITHUB" -> EProvider.GITHUB;
            default -> throw new IllegalArgumentException("Invalid Provider Type.");
        };
    }
}
