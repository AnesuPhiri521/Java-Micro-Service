package com.sehphirry.users.repository;

import com.sehphirry.users.model.Verification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepository extends JpaRepository<Verification,Long> {
}
