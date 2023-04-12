package com.sehphirry.users.dto.response;

import lombok.Data;

@Data
public class CustomDtoResponse {
    private boolean status;
    private String message;

    public CustomDtoResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
