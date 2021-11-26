package com.astra.studenthomebackend.properties.domain.services;



import com.astra.studenthomebackend.properties.domain.models.PropertyImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PropertyImageService {

    PropertyImage createPropertyImage(Long propertyId, PropertyImage propertyImage);
    PropertyImage getPropertyImageById(Long propertyImageId);
    PropertyImage updatePropertyImage(Long propertyImageId, PropertyImage resource);
    ResponseEntity<?> deletePropertyImage(Long propertyImageId);
    PropertyImage getPropertyImageByPropertyIdAndId(Long propertyId, Long propertyImageId);
    Page<PropertyImage> getAllPropertiesImagesByPropertyId(Long propertyId, Pageable pageable);

}
