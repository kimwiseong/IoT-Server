package com.monorama.iot_server.controller.airquality;

import com.monorama.iot_server.annotation.UserId;
import com.monorama.iot_server.dto.ResponseDto;
import com.monorama.iot_server.dto.request.airquality.AQDRealtimeRequestDto;
import com.monorama.iot_server.service.AQDService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/air-quality-data")
public class AQDController {

    private final AQDService airService;

    @PostMapping("/realtime")
    public ResponseDto<?> saveRealtime(@UserId Long userId,
                                       @Valid @RequestBody AQDRealtimeRequestDto requestDto) {
        airService.saveRealtime(userId, requestDto);
        return ResponseDto.ok(null);
    }
}