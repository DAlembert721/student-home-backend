package com.astra.studenthomebackend.properties.domain.repositories;


import com.astra.studenthomebackend.properties.domain.models.Contract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    Page<Contract> findByPropertyId(Long propertyId, Pageable pageable);
    Page<Contract> findByStudentId(Long studentId, Pageable pageable);
}
