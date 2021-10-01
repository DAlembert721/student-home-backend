package com.astra.studenthomebackend.accounts.domain.service;

import com.astra.studenthomebackend.accounts.domain.model.Student;
import org.springframework.http.ResponseEntity;

public interface StudentService {

    Student getStudentByIdAndUserId(Long studentId, Long userId);
    Student createStudent(Long userId, String educationCenterId, Long districtId, Student student);
    Student updateStudent(Long userId, Long studentId,Student studentRequest);
    ResponseEntity<?> deleteStudent(Long userId, Long studentId);
    Student getStudentById(Long studentId);

}
