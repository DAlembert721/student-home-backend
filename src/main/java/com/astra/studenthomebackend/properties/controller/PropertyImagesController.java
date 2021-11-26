package com.astra.studenthomebackend.properties.controller;


import com.astra.studenthomebackend.properties.domain.models.PropertyImage;
import com.astra.studenthomebackend.properties.domain.services.PropertyImageService;
import com.astra.studenthomebackend.properties.resource.PropertyImageResource;
import com.astra.studenthomebackend.properties.resource.SavePropertyImageResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PropertyImagesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PropertyImageService propertyImageService;

    @Operation(summary = "Get PropertyImage By Id",
            description = "Get A PropertyImage given a Id",
            tags = "property-images")
    @GetMapping("/property-images/{propertyImageId}")
    public PropertyImageResource getAllPropertyImageById(@PathVariable(name = "propertyImageId") Long propertyImageId) {
        return convertToResource(propertyImageService.getPropertyImageById(propertyImageId));
    }

    @Operation(summary = "Post PropertyImage",
            description = "Post A New PropertyImage",
            tags = "properties")
    @PostMapping("/properties/{propertyId}/property-images")
    public PropertyImageResource createPropertyImage(
            @PathVariable(name = "propertyId") Long propertyId,
            @RequestBody @Valid SavePropertyImageResource resource) {
        PropertyImage propertyImage = convertToEntity(resource);
        return convertToResource(propertyImageService.createPropertyImage(propertyId, propertyImage));
    }

    @Operation(summary = "Update PropertyImage By Id",
            description = "Update A PropertyImage given a Id",
            tags = "property-images")
    @PutMapping("/property-images/{propertyImageId}")
    public PropertyImageResource updatePropertyImage(
            @PathVariable(name = "propertyImageId") Long propertyImageId,
            @RequestBody @Valid SavePropertyImageResource savePropertyImageResource) {
        PropertyImage propertyImage = convertToEntity(savePropertyImageResource);
        return convertToResource(propertyImageService.updatePropertyImage(propertyImageId, propertyImage));
    }

    @Operation(summary = "Delete PropertyImage By Id",
            description = "Delete A PropertyImage By Id",
            tags = "property-images")
    @DeleteMapping("/property-images/{propertyImageId}")
    public ResponseEntity<?> deletePropertyImageById(@PathVariable(name = "propertyImageId") Long propertyImageId) {
        return propertyImageService.deletePropertyImage(propertyImageId);
    }

    private PropertyImage convertToEntity(SavePropertyImageResource resource) {
        return mapper.map(resource, PropertyImage.class);
    }

    private PropertyImageResource convertToResource(PropertyImage entity) {
        return mapper.map(entity, PropertyImageResource.class);
    }


}
