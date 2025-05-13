package com.monorama.iot_server.service;

import com.monorama.iot_server.domain.AirQualityData;
import com.monorama.iot_server.domain.User;
import com.monorama.iot_server.dto.request.AirQuality.AirQualityDataRequestDto;
import com.monorama.iot_server.repository.AirQualityDataRepository;
import com.monorama.iot_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AirQualityDataService {

    private final UserRepository userRepo;
    private final AirQualityDataRepository airRepo;

    @Transactional
    public void saveRealtime(Long userId, AirQualityDataRequestDto dto) {
        User user = userRepo.findById(userId).orElseThrow();
        AirQualityData entity = dto.toEntity(user);
        airRepo.save(entity);
    }
}
