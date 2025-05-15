package com.monorama.iot_server.security.handler;

import com.monorama.iot_server.constant.Constant;
import com.monorama.iot_server.dto.JwtTokenDto;
import com.monorama.iot_server.repository.UserRepository;
import com.monorama.iot_server.security.info.UserPrincipal;
import com.monorama.iot_server.type.ERole;
import com.monorama.iot_server.util.CookieUtil;
import com.monorama.iot_server.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        JwtTokenDto jwtTokenDto = jwtUtil.generateTokens(userPrincipal.getId(), userPrincipal.getRole());
        userRepository.updateRefreshTokenAndLoginStatus(userPrincipal.getId(), jwtTokenDto.getRefreshToken(),Boolean.TRUE);

        CookieUtil.addCookie(response, Constant.AUTHORIZATION, jwtTokenDto.getAccessToken());
        CookieUtil.addSecureCookie(response, Constant.REAUTHORIZATION, jwtTokenDto.getRefreshToken(), jwtUtil.getWebRefreshTokenExpirationSecond());

        // Guest 즉, 소셜로그인으로 방금 가입한 유저는 따로 로직 분류

        if (userPrincipal.getRole().equals(ERole.GUEST)) {
            response.sendRedirect( "http://localhost:5173/auth/register/social_" + jwtTokenDto.getAccessToken());
        } else {
            response.sendRedirect("http://localhost:5173/_" + jwtTokenDto.getAccessToken());
        }

    }
}
