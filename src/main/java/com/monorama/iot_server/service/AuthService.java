package com.monorama.iot_server.service;

import com.monorama.iot_server.domain.User;
import com.monorama.iot_server.dto.JwtTokenDto;
import com.monorama.iot_server.dto.request.register.AQDUserRegisterDto;
import com.monorama.iot_server.dto.request.register.PMRegisterDto;
import com.monorama.iot_server.exception.CommonException;
import com.monorama.iot_server.exception.ErrorCode;
import com.monorama.iot_server.repository.UserRepository;
import com.monorama.iot_server.type.ERole;
import com.monorama.iot_server.util.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public JwtTokenDto registerPM(Long userId, PMRegisterDto registerDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new CommonException(ErrorCode.NOT_FOUND_USER));
        user.register(registerDto.toEntity(), ERole.PM);

        final JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getId(), user.getRole());
        user.setRefreshToken(jwtTokenDto.getRefreshToken());

        return jwtTokenDto;
    }

    @Transactional
    public JwtTokenDto registerAQDUser(Long userId, AQDUserRegisterDto registerDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new CommonException(ErrorCode.NOT_FOUND_USER));
        user.register(registerDto.toEntity(), ERole.AIR_QUALITY_USER);

        final JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getId(), user.getRole());
        user.setRefreshToken(jwtTokenDto.getRefreshToken());

        return jwtTokenDto;
    }

}
