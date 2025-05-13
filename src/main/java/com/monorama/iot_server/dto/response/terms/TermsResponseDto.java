package com.monorama.iot_server.dto.response.terms;

import com.monorama.iot_server.domain.Terms;

public record TermsResponseDto(
        String type,
        String title,
        String content
) {

    public static TermsResponseDto fromEntity(Terms terms) {
        return new TermsResponseDto(
                terms.getType().toString(),
                terms.getTitle(),
                terms.getContent()
        );
    }
}
