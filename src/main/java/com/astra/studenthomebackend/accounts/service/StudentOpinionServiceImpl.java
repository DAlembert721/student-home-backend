package com.astra.studenthomebackend.accounts.service;

import com.astra.studenthomebackend.accounts.domain.model.StudentOpinion;
import com.astra.studenthomebackend.accounts.domain.repository.LandLordRepository;
import com.astra.studenthomebackend.accounts.domain.repository.StudentOpinionRepository;
import com.astra.studenthomebackend.accounts.domain.repository.StudentRepository;
import com.astra.studenthomebackend.accounts.domain.service.StudentOpinionService;
import com.astra.studenthomebackend.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentOpinionServiceImpl implements StudentOpinionService {

    @Autowired
    private StudentOpinionRepository studentOpinionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private LandLordRepository landLordRepository;

    @Override
    public Page<StudentOpinion> getAllStudentOpinionsByStudentIdAndLandlordId(Long studentId, Long landlordId, Pageable pageable) {
        return studentOpinionRepository.findByStudentIdAndLandLordId(studentId,landlordId,pageable);
    }

    @Override
    public Page<StudentOpinion> getAllStudentOpinionsByStudentId(Long studentId, Pageable pageable) {
        return studentOpinionRepository.findByStudentId(studentId,pageable);
    }

    @Override
    public Page<StudentOpinion> getAllStudentOpinionsByLandlordId(Long landlordId, Pageable pageable) {
        return studentOpinionRepository.findByLandLordId(landlordId,pageable);
    }

    @Override
    public Page<StudentOpinion> getAllStudentOpinions(Pageable pageable) {
        return studentOpinionRepository.findAll(pageable);
    }

    @Override
    public StudentOpinion createStudentOpinion(Long studentId, Long landlordId, StudentOpinion studentOpinion) {
        return landLordRepository.findById(landlordId).map(landLord -> {
            return studentRepository.findById(studentId).map(student-> {
                studentOpinion.setLandLord(landLord);
                studentOpinion.setStudent(student);
                return studentOpinionRepository.save(studentOpinion);
            }).orElseThrow(()->new ResourceNotFoundException(
                    "Student", "Id", studentId));
        }).orElseThrow(() -> new ResourceNotFoundException(
                "landlord", "Id", landlordId));
    }

    @Override
    public StudentOpinion getStudentOpinionByIdAndStudentIdAndLandlordId(Long studentOpinionId, Long studentId, Long landlordId) {
        return studentOpinionRepository.findByIdAndStudentIdAndLandLordId(studentOpinionId,studentId,landlordId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "StudentOpinion not found with Id " + studentOpinionId +
                                " and StudentId " + studentId + " and LandlordId " + landlordId));
    }

    @Override
    public StudentOpinion updateStudentOpinion(Long studentOpinionId, Long studentId, Long landlordId, StudentOpinion studentOpinionRequest) {
        StudentAndLandLordExist(studentId, landlordId);
        return studentOpinionRepository.findById(studentOpinionId).map(studentOpinion -> {
            studentOpinion.setScore(studentOpinionRequest.getScore());
            studentOpinion.setContent(studentOpinionRequest.getContent());
            return studentOpinionRepository.save(studentOpinion);
        }).orElseThrow(() -> new ResourceNotFoundException("StudentOpinion", "Id", studentOpinionId));
    }

    @Override
    public ResponseEntity<?> deleteStudentOpinion(Long studentOpinionId, Long studentId, Long landlordId) {
        StudentAndLandLordExist(studentId, landlordId);
        return studentOpinionRepository.findById(studentOpinionId).map(comment -> {
            studentOpinionRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("StudentOpinion", "Id", studentOpinionId));
    }

    public void StudentAndLandLordExist(Long studentId, Long landlordId) {
        if (!studentRepository.existsById(studentId))
            throw new ResourceNotFoundException("Student", "Id", studentId);
        if (!landLordRepository.existsById(landlordId))
            throw new ResourceNotFoundException("Landlord", "Id", landlordId);
    }

}

