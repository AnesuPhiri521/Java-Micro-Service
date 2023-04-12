package com.sehphirry.users.service.impl;

import com.sehphirry.users.dto.request.CreateUserDto;
import com.sehphirry.users.dto.request.LoginDto;
import com.sehphirry.users.dto.request.OtpDto;
import com.sehphirry.users.dto.response.CustomDtoResponse;
import com.sehphirry.users.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public ResponseEntity<CustomDtoResponse> createAccount(CreateUserDto createUserDto) {
        return null;
    }

    @Override
    public ResponseEntity<CustomDtoResponse> login(LoginDto loginDto) {
        return null;
    }

    @Override
    public ResponseEntity<CustomDtoResponse> verifyOtp(OtpDto otp) {
        return null;
    }
}
