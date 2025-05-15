package com.monorama.iot_server.controller;

import com.monorama.iot_server.annotation.UserId;
import com.monorama.iot_server.dto.ResponseDto;
import com.monorama.iot_server.dto.request.register.UserRegisterDto;
import com.monorama.iot_server.dto.request.register.PMRegisterDto;
import com.monorama.iot_server.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    //소셜 회원가입
    @PatchMapping("/register/pm")
    public ResponseDto<?> registerPM(@UserId Long userId, @RequestBody @Valid PMRegisterDto registerDto) {
        return ResponseDto.created(authService.registerPM(userId, registerDto));
    }

    @PatchMapping("/register/air-quality-data")
    public ResponseDto<?> registerAQDUser(@UserId Long userId, @RequestBody @Valid UserRegisterDto registerDto) {
        return ResponseDto.created(authService.registerAQDUser(userId, registerDto));
    }

    @PatchMapping("/register/air-quality-data/role")
    public ResponseDto<?> updateAQDToBoth(@UserId Long userId) {
        return ResponseDto.ok(authService.updateAQDUserRole(userId));
    }

    @PatchMapping("/register/health-data")
    public ResponseDto<?> registerHDUser(@UserId Long userId, @RequestBody @Valid UserRegisterDto registerDto) {
        return ResponseDto.created(authService.registerHDUser(userId, registerDto));
    }

    @PatchMapping("/register/health-data/role")
    public ResponseDto<?> updateHDToBoth(@UserId Long userId) {
        return ResponseDto.ok(authService.updateHDUserRole(userId));
    }
}
