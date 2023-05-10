package com.sehphirry.notification.dto;

import lombok.Data;

@Data
public class NotificationRequestDto {
    private String to;
    private String subject;
    private String body;
    private String additional1;
    private String additional2;
    private String additional3;
}
