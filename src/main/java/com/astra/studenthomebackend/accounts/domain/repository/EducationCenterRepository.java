package com.astra.studenthomebackend.accounts.domain.repository;

import com.astra.studenthomebackend.accounts.domain.model.EducationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationCenterRepository extends JpaRepository<EducationCenter, Long> {
}
