package com.monorama.iot_server.service;

import com.monorama.iot_server.domain.AirQualityData;
import com.monorama.iot_server.domain.User;
import com.monorama.iot_server.dto.request.airquality.AQDRealtimeRequestDto;
import com.monorama.iot_server.repository.AQDRepository;
import com.monorama.iot_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AQDService {

    private final UserRepository userRepo;
    private final AQDRepository airRepo;

    @Transactional
    public void saveRealtime(Long userId, AQDRealtimeRequestDto dto) {
        User user = userRepo.findById(userId).orElseThrow();
        AirQualityData entity = dto.toEntity(user);
        airRepo.save(entity);
    }
}
