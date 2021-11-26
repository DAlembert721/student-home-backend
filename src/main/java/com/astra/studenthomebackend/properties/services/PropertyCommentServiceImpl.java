package com.astra.studenthomebackend.properties.services;

import com.astra.studenthomebackend.accounts.domain.model.Student;
import com.astra.studenthomebackend.accounts.domain.repository.StudentRepository;
import com.astra.studenthomebackend.properties.domain.models.Property;
import com.astra.studenthomebackend.properties.domain.models.PropertyComment;
import com.astra.studenthomebackend.properties.domain.repositories.PropertyCommentRepository;
import com.astra.studenthomebackend.properties.domain.repositories.PropertyRepository;
import com.astra.studenthomebackend.properties.domain.services.PropertyCommentService;
import com.astra.studenthomebackend.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PropertyCommentServiceImpl implements PropertyCommentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PropertyCommentRepository propertyCommentRepository;

    @Override
    public PropertyComment createPropertyComment(Long studentId, Long propertyId, PropertyComment propertyComment) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student", "Id", studentId));
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
        propertyComment.setStudent(student);
        propertyComment.setProperty(property);
        return  propertyCommentRepository.save(propertyComment);
    }

    @Override
    public Page<PropertyComment> getAllPropertyCommentByPropertyId(Long propertyId, Pageable pageable) {
        if(!propertyRepository.existsById(propertyId))
            throw new ResourceNotFoundException("Property", "Id", propertyId);
        return propertyCommentRepository.findByPropertyId(propertyId, pageable);
    }

    @Override
    public Page<PropertyComment> getAllPropertyCommentsByStudentId(Long studentId, Pageable pageable) {
        if(!studentRepository.existsById(studentId))
            throw new ResourceNotFoundException("Student", "Id", studentId);
        return propertyCommentRepository.findByStudentId(studentId, pageable);
    }

    @Override
    public PropertyComment updatePropertyComment(Long commentId, PropertyComment request) {
        PropertyComment propertyComment = propertyCommentRepository.findById(commentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment", "Id", commentId));
        propertyComment.setComment(request.getComment());
        propertyComment.setScore(request.getScore());
        return propertyCommentRepository.save(propertyComment);
    }

    @Override
    public ResponseEntity<?> deletePropertyComment(Long commentId) {
        PropertyComment propertyComment = propertyCommentRepository.findById(commentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment", "Id", commentId));
        propertyCommentRepository.delete(propertyComment);
        return ResponseEntity.ok().build();
    }
}
