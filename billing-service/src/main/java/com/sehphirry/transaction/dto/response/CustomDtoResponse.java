package com.sehphirry.transaction.dto.response;

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
