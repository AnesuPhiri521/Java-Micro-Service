package com.sehphirry.transaction.repository;

import com.sehphirry.transaction.model.VendorBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorBalanceRepository extends JpaRepository<VendorBalance,Long> {
}
