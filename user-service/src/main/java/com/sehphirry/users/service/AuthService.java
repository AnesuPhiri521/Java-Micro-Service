package com.sehphirry.users.service;

import com.sehphirry.users.dto.request.CreateUserDto;
import com.sehphirry.users.dto.request.LoginDto;
import com.sehphirry.users.dto.request.OtpDto;
import com.sehphirry.users.dto.response.CustomDtoResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<CustomDtoResponse> createAccount(CreateUserDto createUserDto);

    ResponseEntity<CustomDtoResponse> login(LoginDto loginDto);

    ResponseEntity<CustomDtoResponse> verifyOtp(OtpDto otp);
}
