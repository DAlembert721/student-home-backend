package com.astra.studenthomebackend.properties.services;


import com.astra.studenthomebackend.properties.domain.models.Property;
import com.astra.studenthomebackend.properties.domain.models.PropertyImage;
import com.astra.studenthomebackend.properties.domain.repositories.PropertyImageRepository;
import com.astra.studenthomebackend.properties.domain.repositories.PropertyRepository;
import com.astra.studenthomebackend.properties.domain.services.PropertyImageService;
import com.astra.studenthomebackend.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PropertyImageServiceImpl implements PropertyImageService {

    @Autowired
    private PropertyImageRepository propertyImageRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public PropertyImage createPropertyImage(Long propertyId, PropertyImage propertyImage) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
        propertyImage.setProperty(property);
        return propertyImageRepository.save(propertyImage);
    }

    @Override
    public PropertyImage getPropertyImageById(Long propertyImageId) {
        return propertyImageRepository.findById(propertyImageId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("PropertyImage", "Id", propertyImageId));
    }

    @Override
    public PropertyImage updatePropertyImage(Long propertyImageId, PropertyImage resource) {
        PropertyImage propertyImage = propertyImageRepository.findById(propertyImageId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("PropertyImage", "Id", propertyImageId));
        propertyImage.setUrl(resource.getUrl());
        return propertyImageRepository.save(propertyImage);
    }

    @Override
    public ResponseEntity<?> deletePropertyImage(Long propertyImageId) {
        PropertyImage propertyImage = propertyImageRepository.findById(propertyImageId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("PropertyImage", "Id", propertyImageId));
        propertyImageRepository.delete(propertyImage);
        return ResponseEntity.ok().build();
    }

    @Override
    public PropertyImage getPropertyImageByPropertyIdAndId(Long propertyId, Long propertyImageId) {
        if (!propertyRepository.existsById(propertyId)) {
            throw  new ResourceNotFoundException("Property", "Id", propertyId);
        }
        return propertyImageRepository.findById(propertyImageId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("PropertyImage", "Id", propertyImageId));

    }


    @Override
    public Page<PropertyImage> getAllPropertiesImagesByPropertyId(Long propertyId, Pageable pageable) {
        if (!propertyRepository.existsById(propertyId)) {
            throw  new ResourceNotFoundException("Property", "Id", propertyId);
        }
        return propertyImageRepository.findByPropertyId(propertyId, pageable);
    }
}
