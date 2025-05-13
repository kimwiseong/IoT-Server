package com.monorama.iot_server.controller.AirQuality;

import com.monorama.iot_server.annotation.UserId;
import com.monorama.iot_server.dto.ResponseDto;
import com.monorama.iot_server.dto.request.AirQuality.AirQualityDataRequestDto;
import com.monorama.iot_server.service.AirQualityDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/air-quality-data")
public class AirQualityDataController {

    private final AirQualityDataService airService;

    @PostMapping("/realtime")
    public ResponseDto<?> saveRealtime(@UserId Long userId,
                                       @RequestBody AirQualityDataRequestDto requestDto) {
        airService.saveRealtime(userId, requestDto);
        return ResponseDto.ok(null);
    }
}
