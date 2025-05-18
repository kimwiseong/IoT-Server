package com.monorama.iot_server.service;

import com.monorama.iot_server.domain.User;
import com.monorama.iot_server.domain.UserDataPermission;
import com.monorama.iot_server.dto.JwtTokenDto;
import com.monorama.iot_server.dto.request.register.UserRegisterDto;
import com.monorama.iot_server.dto.request.register.PMRegisterDto;
import com.monorama.iot_server.exception.CommonException;
import com.monorama.iot_server.exception.ErrorCode;
import com.monorama.iot_server.repository.UserDataPermissonRepository;
import com.monorama.iot_server.repository.UserRepository;
import com.monorama.iot_server.type.ERole;
import com.monorama.iot_server.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserDataPermissonRepository userDataPermissonRepository;
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
    public JwtTokenDto registerAQDUser(Long userId, UserRegisterDto registerDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new CommonException(ErrorCode.NOT_FOUND_USER));
        user.register(registerDto.toEntity(), ERole.AQD_USER);

        UserDataPermission userDataPermission = new UserDataPermission(user);
        userDataPermissonRepository.save(userDataPermission);

        final JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getId(), user.getRole());
        user.setRefreshToken(jwtTokenDto.getRefreshToken());

        return jwtTokenDto;
    }

    @Transactional
    public JwtTokenDto registerHDUser(Long userId, UserRegisterDto registerDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new CommonException(ErrorCode.NOT_FOUND_USER));
        user.register(registerDto.toEntity(), ERole.HD_USER);

        UserDataPermission userDataPermission = new UserDataPermission(user);
        userDataPermissonRepository.save(userDataPermission);

        final JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getId(), user.getRole());
        user.setRefreshToken(jwtTokenDto.getRefreshToken());

        return jwtTokenDto;
    }

    @Transactional
    public JwtTokenDto updateHDUserRole(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new CommonException(ErrorCode.NOT_FOUND_USER));

        if (user.getRole() != ERole.AQD_USER) {
            throw new CommonException(ErrorCode.ACCESS_DENIED_ERROR);
        }
        user.updateRoleToBoth();

        final JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getId(), user.getRole());
        user.setRefreshToken(jwtTokenDto.getRefreshToken());

        return jwtTokenDto;
    }

    @Transactional
    public JwtTokenDto updateAQDUserRole(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new CommonException(ErrorCode.NOT_FOUND_USER));

        if (user.getRole() != ERole.HD_USER) {
            throw new CommonException(ErrorCode.ACCESS_DENIED_ERROR);
        }
        user.updateRoleToBoth();

        final JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getId(), user.getRole());
        user.setRefreshToken(jwtTokenDto.getRefreshToken());

        return jwtTokenDto;
    }

    public JwtTokenDto refresh(String refreshToken, HttpServletRequest request) {
        Long userId = jwtUtil.extractUserIdFromToken(refreshToken);

        User user = userRepository.findUserByIdAndIsLoginAndRefreshTokenIsNotNull(userId, true)
                .orElseThrow(() -> new CommonException(ErrorCode.INVALID_TOKEN_ERROR));

        if (!refreshToken.equals(user.getRefreshToken())) {
            throw new CommonException(ErrorCode.TOKEN_TYPE_ERROR);
        }

        JwtTokenDto newTokens = jwtUtil.generateTokens(userId, user.getRole());
        userRepository.updateRefreshTokenAndLoginStatus(userId, newTokens.getRefreshToken(), true);

        return newTokens;
    }

}
