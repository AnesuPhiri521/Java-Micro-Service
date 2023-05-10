package com.sehphirry.users.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@NoArgsConstructor
public class CustomDtoResponse {
    @JsonProperty("status")
    private boolean status;
    @JsonProperty("message")
    private String message;

    public CustomDtoResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
