package com.sehphirry.transaction.service;

import com.sehphirry.transaction.dto.response.CustomDtoResponse;
import org.springframework.http.ResponseEntity;

public interface BillingService {
    ResponseEntity<CustomDtoResponse> initVendorBalance(Long vendorId);
}
