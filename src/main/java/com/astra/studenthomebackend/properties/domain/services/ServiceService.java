package com.astra.studenthomebackend.properties.domain.services;


import com.astra.studenthomebackend.properties.domain.models.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceService {
    Page<Service> getAllService(Pageable pageable);
    Service getServiceById(Long serviceId);
    Page<Service> getAllServicesByPropertyId(Long propertyId, Pageable pageable);
}
