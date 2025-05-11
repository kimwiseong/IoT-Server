package com.monorama.iot_server.controller.HealthData;

import com.monorama.iot_server.domain.type.ServiceType;
import com.monorama.iot_server.dto.ResponseDto;
import com.monorama.iot_server.dto.response.terms.TermsListResponseDto;
import com.monorama.iot_server.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health-data/terms")
@RequiredArgsConstructor
public class HealthTermsController {

    private final TermsService termsService;

    @GetMapping
    public ResponseDto<TermsListResponseDto> getTerms() {
        TermsListResponseDto result = termsService.getTermsByProjectType(ServiceType.HEALTH_DATA);
        return ResponseDto.ok(result);
    }
}