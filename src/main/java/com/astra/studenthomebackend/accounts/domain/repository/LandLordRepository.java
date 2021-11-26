package com.astra.studenthomebackend.accounts.domain.repository;

import com.astra.studenthomebackend.accounts.domain.model.LandLord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LandLordRepository extends JpaRepository<LandLord, Long> {
    Optional<LandLord> findByIdAndUserId(Long landLordId, Long userId);
}
