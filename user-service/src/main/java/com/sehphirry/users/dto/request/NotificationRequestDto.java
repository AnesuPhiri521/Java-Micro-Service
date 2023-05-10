package com.sehphirry.users.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotificationRequestDto {
    private String to;
    private String subject;
    private String body;
    private String additional1;
    private String additional2;
    private String additional3;
}
