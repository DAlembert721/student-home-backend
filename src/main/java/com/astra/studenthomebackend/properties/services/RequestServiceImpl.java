package com.astra.studenthomebackend.properties.services;


import com.astra.studenthomebackend.accounts.domain.model.Student;
import com.astra.studenthomebackend.accounts.domain.repository.StudentRepository;
import com.astra.studenthomebackend.properties.domain.models.ERequestStatus;
import com.astra.studenthomebackend.properties.domain.models.Property;
import com.astra.studenthomebackend.properties.domain.models.Request;
import com.astra.studenthomebackend.properties.domain.repositories.PropertyRepository;
import com.astra.studenthomebackend.properties.domain.repositories.RequestRepository;
import com.astra.studenthomebackend.properties.domain.services.RequestService;
import com.astra.studenthomebackend.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Request createRequest(Long studentId, Long propertyId, Request request) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student", "Id", studentId));
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
        request.setStudent(student);
        request.setProperty(property);
        request.setState(ERequestStatus.UNRESOLVED);
        return  requestRepository.save(request);
    }

    @Override
    public Page<Request> getAllRequestsByPropertyId(Long propertyId, Pageable pageable) {

        if(!propertyRepository.existsById(propertyId))
            throw new ResourceNotFoundException("Property", "Id", propertyId);
        return requestRepository.findByPropertyId(propertyId, pageable);
    }

    @Override
    public Page<Request> getAllRequestsByStudentIdId(Long studentId, Pageable pageable) {
        if(!studentRepository.existsById(studentId))
            throw new ResourceNotFoundException("Student", "Id", studentId);
        return requestRepository.findByStudentId(studentId, pageable);
    }

    @Override
    public Request updateRequest(Long requestId, Request resource) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request", "Id", requestId));
        request.setContent(resource.getContent());
        request.setState(resource.getState());
        return requestRepository.save(request);
    }

    @Override
    public ResponseEntity<?> deleteRequest(Long requestId) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request", "Id", requestId));
        requestRepository.delete(request);
        return ResponseEntity.ok().build();
    }

    @Override
    public Request updateStateOfRequest(Long requestId, ERequestStatus state) {
        Request request = requestRepository.findById(requestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Request", "Id", requestId));
        request.setState(state);
        return requestRepository.save(request);
    }
}
