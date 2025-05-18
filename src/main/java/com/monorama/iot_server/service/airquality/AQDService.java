package com.monorama.iot_server.service.airquality;

import com.monorama.iot_server.domain.AirQualityData;
import com.monorama.iot_server.domain.User;
import com.monorama.iot_server.dto.request.airquality.AQDRequestDto;
import com.monorama.iot_server.dto.request.airquality.AQDSyncRequestDto;
import com.monorama.iot_server.exception.CommonException;
import com.monorama.iot_server.exception.ErrorCode;
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
    public void saveRealtime(Long userId, AQDRequestDto dto) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        AirQualityData entity = dto.toEntity(user);
        airRepo.save(entity);
    }


    @Transactional
    public void saveSync(Long userId, AQDSyncRequestDto request) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        airRepo.saveAll(
                request.airQualityDataList().stream()
                        .map(dto -> dto.toEntity(user))
                        .toList()
        );
    }
}
