package com.sehphirry.users.repository;

import com.sehphirry.users.model.VendorClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorClientsRepository extends JpaRepository<VendorClient,Long> {
}
