package com.monorama.iot_server.controller.HealthData;

import com.monorama.iot_server.dto.ResponseDto;
import com.monorama.iot_server.dto.response.terms.TermsListResponseDto;
import com.monorama.iot_server.service.TermsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health-data/terms")
@RequiredArgsConstructor
public class HealthTermsController {

    private final TermsService termsService;

    @GetMapping
    public ResponseEntity<ResponseDto<TermsListResponseDto>> getTerms() {
        TermsListResponseDto result = termsService.getHealthSignupTerms();
        return ResponseEntity.ok(ResponseDto.ok(result));
    }
}


