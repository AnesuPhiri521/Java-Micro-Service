package com.sehphirry.users.dto.request;

import lombok.Data;

@Data
public class LoginDto {
    private String email;
    private String password;
}
