package com.monorama.iot_server.config;

import com.monorama.iot_server.constant.Constant;
import com.monorama.iot_server.security.JwtAuthEntryPoint;
import com.monorama.iot_server.security.JwtAuthenticationProvider;
import com.monorama.iot_server.security.filter.JwtAuthenticationFilter;
import com.monorama.iot_server.security.filter.JwtExceptionFilter;
import com.monorama.iot_server.security.handler.*;
import com.monorama.iot_server.security.service.CustomOAuth2UserService;
import com.monorama.iot_server.security.service.CustomUserDetailService;
import com.monorama.iot_server.type.ERole;
import com.monorama.iot_server.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final DefaultSuccessHandler defaultSuccessHandler;
    private final DefaultFaiureHandler defaultFailureHandler;

    private final CustomUserDetailService customUserDetailService;
    private final CustomOAuth2UserService customOAuth2UserService;

    private final JwtUtil jwtUtil;

    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final CustomLogOutProcessHandler customLogOutProcessHandler;
    private final CustomLogOutResultHandler customLogOutResultHandler;

    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;


    @Bean
    protected SecurityFilterChain securityFilterChain(final HttpSecurity httpSecurity) throws Exception {


        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(requestMatcherRegistry -> requestMatcherRegistry
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/projects/**").hasAnyRole((ERole.PM.toString()),ERole.BOTH_USER.toString(), ERole.AQD_USER.toString(), ERole.HD_USER.toString())
                        .requestMatchers("/health-data/**").hasAnyRole((ERole.BOTH_USER.toString()),ERole.HD_USER.toString())
                        .requestMatchers("/air-quality-data/**").hasAnyRole((ERole.BOTH_USER.toString()), ERole.AQD_USER.toString())
                        .anyRequest().authenticated())

                .oauth2Login(
                        configurer ->
                                configurer
                                        .successHandler(oAuth2LoginSuccessHandler)
                                        .failureHandler(oAuth2LoginFailureHandler)
                                        .userInfoEndpoint(userInfoEndpoint ->
                                                userInfoEndpoint.userService(customOAuth2UserService))
                )
                .logout(configurer ->
                        configurer
                                .logoutUrl("/auth/logout")
                                .addLogoutHandler(customLogOutProcessHandler)
                                .logoutSuccessHandler(customLogOutResultHandler)
                                .deleteCookies(Constant.AUTHORIZATION,Constant.REAUTHORIZATION)
                )
                .exceptionHandling(configurer ->
                        configurer
                                .authenticationEntryPoint(jwtAuthEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler)
                )


                .addFilterBefore(new JwtAuthenticationFilter(jwtUtil, new JwtAuthenticationProvider(customUserDetailService)), LogoutFilter.class)
                .addFilterBefore(new JwtExceptionFilter(), JwtAuthenticationFilter.class)
                .getOrBuild();
    }
}
