package com.sehphirry.transaction.service.Impl;

import com.sehphirry.transaction.dto.response.CustomDtoResponse;
import com.sehphirry.transaction.model.VendorBalance;
import com.sehphirry.transaction.repository.VendorBalanceRepository;
import com.sehphirry.transaction.service.BillingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BillingServiceImpl implements BillingService {
    private final VendorBalanceRepository vendorBalanceRepository;
    @Override
    public ResponseEntity<CustomDtoResponse> initVendorBalance(Long vendorId) {
        VendorBalance vendorBalance = VendorBalance.builder()
                .vendorId(vendorId)
                .usdAmount(.0)
                .zwlAmount(.0)
                .build();
        log.info("Vendor initial balance {}",vendorBalance);

        vendorBalanceRepository.save(vendorBalance);
        return new ResponseEntity<>(new CustomDtoResponse(true,"Initial Balances Settled"), HttpStatus.OK);
    }
}
