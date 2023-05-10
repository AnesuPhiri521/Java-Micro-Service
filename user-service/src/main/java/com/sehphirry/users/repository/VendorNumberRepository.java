package com.sehphirry.users.repository;

import com.sehphirry.users.model.VendorNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorNumberRepository extends JpaRepository<VendorNumber,Long> {
    VendorNumber findByVendorNumber(String vendorNumber);
}
