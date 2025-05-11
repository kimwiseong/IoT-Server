package com.monorama.iot_server.domain;

import com.monorama.iot_server.domain.type.TermsType;
import com.monorama.iot_server.dto.response.terms.TermsResponseDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "terms_tb")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "terms_type", nullable = false)
    private TermsType type;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    public TermsResponseDto toDto() {
        return TermsResponseDto.of(type.toString(), title, content);
    }
}
