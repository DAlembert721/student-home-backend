package com.astra.studenthomebackend.properties.domain.services;


import com.astra.studenthomebackend.properties.domain.models.PropertyComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PropertyCommentService {
    PropertyComment createPropertyComment(Long studentId, Long propertyId, PropertyComment request);
    Page<PropertyComment> getAllPropertyCommentByPropertyId(Long propertyId, Pageable pageable);
    Page<PropertyComment> getAllPropertyCommentsByStudentId(Long studentId, Pageable pageable);
    PropertyComment updatePropertyComment(Long commentId, PropertyComment request);
    ResponseEntity<?> deletePropertyComment(Long commentId);
}
