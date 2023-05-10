package com.sehphirry.users.repository;

import com.sehphirry.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByEmail(String email);
    Optional<Users> findUsersByEmail(String email);
}
