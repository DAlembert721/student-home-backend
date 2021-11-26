package com.astra.studenthomebackend.properties.domain.services;

import com.astra.studenthomebackend.properties.domain.models.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface PropertyService {
    Page<Property> getAllProperties(Pageable pageable);
    Property createProperty(Long landLordId, Long districtId, Property property);
    Property getPropertyById(Long propertyId);
    Property updateProperty(Long landLordId, Long propertyId, Long districtId, Property propertyRequest);
    ResponseEntity<?> deleteProperty(Long landLordId, Long propertyId);
    Property assignPropertyService(Long propertyId, Long serviceId);
    Property unAssignPropertyService(Long propertyId, Long serviceId);
    Page<Property> getAllPropertiesByServiceId(Long serviceId, Pageable pageable);
    Page<Property> getAllPropertiesByLandLordId(Long landLordId, Pageable pageable);
    Page<Property> getAllActiveProperties(Boolean active, Pageable pageable);
    byte[] generateReports(Pageable pageable);
}
