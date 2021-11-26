package com.astra.studenthomebackend.properties.controller;


import com.astra.studenthomebackend.properties.domain.models.Property;
import com.astra.studenthomebackend.properties.domain.services.PropertyService;
import com.astra.studenthomebackend.properties.resource.PropertyResource;
import com.astra.studenthomebackend.properties.resource.SavePropertyResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PropertyServicesController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PropertyService propertyService;

    @Operation(summary = "Assign Service To Property", description = "Add a new service to an existing property", tags = "properties")
    @PostMapping("/properties/{propertyId}/services/{serviceId}")
    public PropertyResource assignPropertyService(
            @PathVariable(name = "propertyId") Long propertyId,
            @PathVariable(name = "serviceId") Long serviceId) {
        return convertToResource(propertyService.assignPropertyService(propertyId, serviceId));
    }

    @Operation(summary = "UnAssign Service To Property", description = "Remove an existing service to an existing property", tags = "properties")
    @DeleteMapping("/properties/{propertyId}/services/{serviceId}")
    public PropertyResource unAssignPropertyService(
            @PathVariable(name = "propertyId") Long propertyId,
            @PathVariable(name = "serviceId") Long serviceId) {
        return convertToResource(propertyService.unAssignPropertyService(propertyId, serviceId));
    }

    private Property convertToEntity(SavePropertyResource resource) {
        return mapper.map(resource, Property.class);
    }

    private PropertyResource convertToResource(Property entity) {
        return mapper.map(entity, PropertyResource.class);
    }


}
