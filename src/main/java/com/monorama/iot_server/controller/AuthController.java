package com.monorama.iot_server.controller;

import com.monorama.iot_server.annotation.UserId;
import com.monorama.iot_server.dto.ResponseDto;
import com.monorama.iot_server.dto.request.register.AQDUserRegisterDto;
import com.monorama.iot_server.dto.request.register.PMRegisterDto;
import com.monorama.iot_server.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService userService;

    //소셜 회원가입
    @PostMapping("/register/pm")
    public ResponseDto<?> registerPM(@UserId Long userId, @RequestBody @Valid PMRegisterDto registerDto) {
        return ResponseDto.created(userService.registerPM(userId, registerDto));
    }

    @PostMapping("/register/air-quality-data")
    public ResponseDto<?> registerAQDUser(@UserId Long userId, @RequestBody @Valid AQDUserRegisterDto registerDto) {
        return ResponseDto.created(userService.registerAQDUser(userId, registerDto));
    }
}
