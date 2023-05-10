package com.sehphirry.transaction.controller;

import com.sehphirry.transaction.dto.response.CustomDtoResponse;
import com.sehphirry.transaction.service.BillingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/billing")
@RequiredArgsConstructor
public class BillingController {

    private final BillingService billingService;
    @GetMapping("/initial-vendor-balance/{vendorId}")
    public ResponseEntity<CustomDtoResponse> initVendorBalance(@PathVariable Long vendorId){
        return billingService.initVendorBalance(vendorId);
    }
}
