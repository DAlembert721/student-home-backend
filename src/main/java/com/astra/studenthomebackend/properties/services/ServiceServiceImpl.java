package com.astra.studenthomebackend.properties.services;


import com.astra.studenthomebackend.properties.domain.repositories.PropertyRepository;
import com.astra.studenthomebackend.properties.domain.repositories.ServiceRepository;
import com.astra.studenthomebackend.properties.domain.services.ServiceService;
import com.astra.studenthomebackend.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Page<com.astra.studenthomebackend.properties.domain.models.Service> getAllService(Pageable pageable) {
        return serviceRepository.findAll(pageable);
    }

    @Override
    public com.astra.studenthomebackend.properties.domain.models.Service getServiceById(Long serviceId) {
        return serviceRepository.findById(serviceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Service", "Id", serviceId));
    }

    @Override
    public Page<com.astra.studenthomebackend.properties.domain.models.Service> getAllServicesByPropertyId(Long propertyId, Pageable pageable) {
        return propertyRepository.findById(propertyId)
                .map(property -> {
                    List<com.astra.studenthomebackend.properties.domain.models.Service> services = property.getServices();
                    return new PageImpl<>(services, pageable, services.size());
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
    }
}
