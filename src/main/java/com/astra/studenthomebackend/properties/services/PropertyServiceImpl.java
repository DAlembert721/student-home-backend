package com.astra.studenthomebackend.properties.services;

import com.astra.studenthomebackend.exceptions.ResourceNotFoundException;
import com.astra.studenthomebackend.properties.domain.models.Property;
import com.astra.studenthomebackend.properties.domain.repositories.PropertyRepository;
import com.astra.studenthomebackend.properties.domain.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public List<Property> getAll() {
        return propertyRepository.findAll();
    }

    @Override
    public Property getById(Long id) {
        return propertyRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "id", id));
    }

    @Override
    public Property create(Property property) {
        return null;
    }

    @Override
    public Property update(Long id, Property property) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        propertyRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
