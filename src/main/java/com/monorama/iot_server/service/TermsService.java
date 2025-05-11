package com.monorama.iot_server.service;

import com.monorama.iot_server.domain.Terms;
import com.monorama.iot_server.domain.type.TermsType;
import com.monorama.iot_server.dto.response.terms.TermsListResponseDto;
import com.monorama.iot_server.dto.response.terms.TermsResponseDto;
import com.monorama.iot_server.repository.TermsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TermsService {

    private final TermsRepository termsRepository;

    public TermsListResponseDto getHealthSignupTerms() {
        List<Terms> termsList = termsRepository.findByTypeIn(List.of(
                TermsType.PRIVACY_POLICY,
                TermsType.TERMS_OF_SERVICE
        ));

        List<TermsResponseDto> dtoList = termsList.stream()
                .map(TermsResponseDto::fromEntity)
                .collect(Collectors.toList());


        return TermsListResponseDto.of(dtoList);
    }
}

