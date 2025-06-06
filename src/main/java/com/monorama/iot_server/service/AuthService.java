package com.monorama.iot_server.service;

import com.monorama.iot_server.config.ElasticsearchProperties;
import com.monorama.iot_server.domain.User;
import com.monorama.iot_server.domain.UserDataPermission;
import com.monorama.iot_server.dto.JwtTokenDto;
import com.monorama.iot_server.dto.request.auth.AppleLoginRequestDto;
import com.monorama.iot_server.dto.request.register.UserRegisterDto;
import com.monorama.iot_server.dto.request.register.PMRegisterDto;
import com.monorama.iot_server.exception.CommonException;
import com.monorama.iot_server.exception.ErrorCode;
import com.monorama.iot_server.repository.UserDataPermissionRepository;
import com.monorama.iot_server.repository.UserRepository;
import com.monorama.iot_server.security.apple.AppleTokenVerifier;
import com.monorama.iot_server.type.EProvider;
import com.monorama.iot_server.type.ERole;
import com.monorama.iot_server.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final UserDataPermissionRepository userDataPermissionRepository;
    private final JwtUtil jwtUtil;
    private final AppleTokenVerifier appleTokenVerifier;
    private final ElasticsearchProperties elasticsearchProperties;


    @Transactional
    public JwtTokenDto loginWithAppleForApp(AppleLoginRequestDto appleLoginRequestDto) {
        String socialId = appleTokenVerifier.verifyAndGetUserId(appleLoginRequestDto.identityToken());

        User user = userRepository.findBySocialIdAndEProviderForApp(socialId, EProvider.APPLE)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .role(ERole.GUEST)
                            .socialId(socialId)
                            .provider(EProvider.APPLE)
                            .build();
                    return userRepository.save(newUser);
                });

        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(user.getId(), user.getRole());
        user.setRefreshToken(jwtTokenDto.getRefreshToken());
        user.setIsLogin(true);

        return jwtTokenDto;
    }

    @Transactional
    public JwtTokenDto registerPM(Long userId, PMRegisterDto registerDto) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new CommonException(ErrorCode.NOT_FOUND_USER));
        user.register(registerDto.toEntity(), ERole.PM);

        createKibanaUser(userId, "monorama_pm_role");

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
        userDataPermissionRepository.save(userDataPermission);

        createKibanaUser(userId, "monorama_user_role");

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
        userDataPermissionRepository.save(userDataPermission);

        createKibanaUser(userId, "monorama_user_role");

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

    @Transactional
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

    @Transactional
    public void withdrawUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND_USER));

        user.withdrawUser();
    }

    @Transactional
    public void createKibanaUser(Long userId, String roleName) {
        String userName = switch (roleName) {
            case "monorama_pm_role" -> "manager" + userId;
            default -> "user" + userId;
        };

        String password = "user" + userId + "password";
        String userCreateUrl = elasticsearchProperties.getUrl() + "/_security/user/" + userName;

        String body = """
        {
          "password": "%s",
          "roles": ["%s"],
          "full_name": "%s",
          "metadata": {
            "userId": %d,
            "projectIds": []
          }
        }
        """.formatted(password, roleName, userName, userId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(
                elasticsearchProperties.getUsername(),
                elasticsearchProperties.getPassword()
        );

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = new RestTemplate().exchange(
                userCreateUrl,
                HttpMethod.PUT,
                request,
                String.class
        );

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new CommonException(ErrorCode.EXTERNAL_API_ERROR);
        }
    }
}
