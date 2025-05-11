package com.monorama.iot_server.dto.response.terms;

public record TermsResponseDto(
        String type,
        String title,
        String content
) {
    public static TermsResponseDto of(String type, String title, String content) {
        return new TermsResponseDto(type, title, content);
    }
}
