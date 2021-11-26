package com.astra.studenthomebackend.properties.domain.repositories;


import com.astra.studenthomebackend.properties.domain.models.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
    Page<Request> findByStudentId(Long studentId, Pageable pageable);
    Page<Request> findByPropertyId(Long propertyId, Pageable pageable);
}
