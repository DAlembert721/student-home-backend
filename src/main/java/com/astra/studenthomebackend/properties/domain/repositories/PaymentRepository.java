package com.astra.studenthomebackend.properties.domain.repositories;

import com.astra.studenthomebackend.properties.domain.models.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Page<Payment> findByContractId(Long contractId, Pageable pageable);
}
