package com.astra.studenthomebackend.properties.controller;


import com.astra.studenthomebackend.properties.domain.models.PropertyImage;
import com.astra.studenthomebackend.properties.domain.services.PropertyImageService;
import com.astra.studenthomebackend.properties.resource.PropertyImageResource;
import com.astra.studenthomebackend.properties.resource.SavePropertyImageResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PropertyPropertyImagesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PropertyImageService propertyImageService;


    @Operation(summary = "Get All PropertyImages By PropertyId",
            description = "Get all PropertyImages for a property given a PropertyId",
            tags = {"properties"})
    @GetMapping("/properties/{propertyId}/property-images")
    public Page<PropertyImageResource> getAllPropertyImageByPropertyId(
            @PathVariable(name = "propertyId") Long propertyId,
            Pageable pageable){
        Page<PropertyImage> propertyImagePage = propertyImageService.getAllPropertiesImagesByPropertyId(propertyId, pageable);
        List<PropertyImageResource> propertyImageResources = propertyImagePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(propertyImageResources, pageable, propertyImageResources.size());
    }

    private PropertyImage convertToEntity(SavePropertyImageResource resource) {
        return mapper.map(resource, PropertyImage.class);
    }

    private PropertyImageResource convertToResource(PropertyImage entity) {
        return mapper.map(entity, PropertyImageResource.class);
    }

}
