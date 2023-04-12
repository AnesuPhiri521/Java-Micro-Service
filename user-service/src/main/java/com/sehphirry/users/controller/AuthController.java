package com.sehphirry.users.controller;

import com.sehphirry.users.dto.request.CreateUserDto;
import com.sehphirry.users.dto.request.LoginDto;
import com.sehphirry.users.dto.request.OtpDto;
import com.sehphirry.users.dto.response.CustomDtoResponse;
import com.sehphirry.users.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<CustomDtoResponse> createAccount(@RequestBody CreateUserDto createUserDto){
        return authService.createAccount(createUserDto);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomDtoResponse> auth(@RequestBody LoginDto loginDto){
        return authService.login(loginDto);
    }

    @PostMapping("/verify-opt")
    public ResponseEntity<CustomDtoResponse> VerifyOtp(@RequestBody OtpDto otp){
        return authService.verifyOtp(otp);
    }
}
