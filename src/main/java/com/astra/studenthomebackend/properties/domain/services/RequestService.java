package com.astra.studenthomebackend.properties.domain.services;



import com.astra.studenthomebackend.properties.domain.models.ERequestStatus;
import com.astra.studenthomebackend.properties.domain.models.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RequestService {
    Request createRequest(Long studentId, Long propertyId, Request request);
    Page<Request> getAllRequestsByPropertyId(Long propertyId, Pageable pageable);
    Page<Request> getAllRequestsByStudentIdId(Long studentId, Pageable pageable);
    Request updateRequest(Long requestId, Request resource);
    ResponseEntity<?> deleteRequest(Long requestId);
    Request updateStateOfRequest(Long requestId, ERequestStatus state);
}
