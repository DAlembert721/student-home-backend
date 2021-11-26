package com.astra.studenthomebackend.accounts.service;

import com.astra.studenthomebackend.accounts.domain.model.EducationCenter;
import com.astra.studenthomebackend.accounts.domain.model.Student;
import com.astra.studenthomebackend.accounts.domain.model.auth.User;
import com.astra.studenthomebackend.accounts.domain.repository.EducationCenterRepository;
import com.astra.studenthomebackend.accounts.domain.repository.StudentRepository;
import com.astra.studenthomebackend.accounts.domain.repository.UserRepository;
import com.astra.studenthomebackend.accounts.domain.service.StudentService;
import com.astra.studenthomebackend.locations.domain.model.District;
import com.astra.studenthomebackend.locations.domain.repository.DistrictRepository;
import com.astra.studenthomebackend.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private EducationCenterRepository educationCenterRepository;

    @Override
    public Student getStudentByIdAndUserId(Long studentId, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        return studentRepository.findByIdAndUserId(studentId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "Id", studentId));
    }

    @Override
    public Student createStudent(Long userId, Long educationCenterId, Long districtId, Student student) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

        District district = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District", "Id", districtId));
        EducationCenter educationCenter = educationCenterRepository.findById(educationCenterId)
                        .orElseThrow(() -> new ResourceNotFoundException("EducationCenter", "Id", educationCenterId));

        student.setUser(user);
        student.setEducationCenter(educationCenter);
        student.setDistrict(district);
        student.setId(user.getId());

        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long userId, Long studentId, Student studentRequest) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
        student.setDni(studentRequest.getDni());
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setPhone(studentRequest.getPhone());
        student.setImage(studentRequest.getImage());
        student.setDistrict(studentRequest.getDistrict());
        return studentRepository.save(student);
    }

    @Override
    public ResponseEntity<?> deleteStudent(Long userId, Long studentId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "Id", userId);
        }
        Student student = studentRepository.findByIdAndUserId(studentId, userId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "Id", studentId));
        studentRepository.delete(student);
        return ResponseEntity.ok().build();
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student", "Id", studentId));
    }

}
