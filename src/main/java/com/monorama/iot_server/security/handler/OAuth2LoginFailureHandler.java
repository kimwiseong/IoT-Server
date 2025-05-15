package com.monorama.iot_server.security.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class OAuth2LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // 실패 원인 로그 기록
        log.error("OAuth2 Login failed for request {} with exception: {}", request.getRequestURI(), exception.getMessage(), exception);

        // 필요한 경우 exception 정보를 바탕으로 더 세부적인 로그를 남길 수 있음
        if (exception instanceof OAuth2AuthenticationException oAuth2Exception) {
            log.error("OAuth2 Error: {}", oAuth2Exception.getMessage());
        }

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.getWriter().write("Social Login Fail "+exception.getMessage());
    }
}
