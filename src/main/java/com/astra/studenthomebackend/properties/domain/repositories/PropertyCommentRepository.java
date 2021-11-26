package com.astra.studenthomebackend.properties.domain.repositories;

import com.astra.studenthomebackend.properties.domain.models.PropertyComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyCommentRepository extends JpaRepository<PropertyComment, Long> {
    Page<PropertyComment> findByStudentId(Long studentId, Pageable pageable);
    Page<PropertyComment> findByPropertyId(Long propertyId, Pageable pageable);
}
