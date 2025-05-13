package com.monorama.iot_server.dto.response.terms;

import java.util.List;

public record TermsListResponseDto(
        List<TermsResponseDto> terms
) {
    public static TermsListResponseDto of(List<TermsResponseDto> terms) {
        return new TermsListResponseDto(terms);
    }
}
