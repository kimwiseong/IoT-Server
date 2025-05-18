package com.monorama.iot_server.service.healthdata;

import com.monorama.iot_server.domain.User;
import com.monorama.iot_server.dto.request.healthdata.HDRequestDto;
import com.monorama.iot_server.dto.request.healthdata.HDSyncRequestDto;
import com.monorama.iot_server.exception.CommonException;
import com.monorama.iot_server.exception.ErrorCode;
import com.monorama.iot_server.repository.HDRepository;
import com.monorama.iot_server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HDService {

    private final UserRepository userRepository;
    private final HDRepository HDRepository;

    @Transactional
    public void saveRealtime(Long userId, HDRequestDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        HDRepository.save(dto.toEntity(user));
    }


    @Transactional
    public void saveSync(Long userId, HDSyncRequestDto request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));
        HDRepository.saveAll(
                request.healthDataList().stream()
                        .map(dto -> dto.toEntity(user))
                        .toList()
        );
    }
}
