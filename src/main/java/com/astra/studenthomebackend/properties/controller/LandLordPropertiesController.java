package com.astra.studenthomebackend.properties.controller;


import com.astra.studenthomebackend.properties.domain.models.Property;
import com.astra.studenthomebackend.properties.domain.services.PropertyService;
import com.astra.studenthomebackend.properties.resource.PropertyResource;
import com.astra.studenthomebackend.properties.resource.SavePropertyResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class LandLordPropertiesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PropertyService propertyService;

    @Operation(summary = "Post Property of a Land Lord",
            description = "Post AProperty of a Land Lord",
            tags = "landlords")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Property created",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/landlords/{landLordId}/properties")
    public PropertyResource createProperty(
            @PathVariable(name = "landLordId") Long landLordId,
            @Valid @RequestBody SavePropertyResource resource) {
        Property property = convertToEntity(resource);
        return convertToResource(propertyService.createProperty(landLordId,resource.getPlace(), property));

    }
    @Operation(summary = "Get Properties By Landlord Id",
            description = "Get All Properties given a Landlord Id",
            tags = "landlords")
    @GetMapping("/landlords/{landLordId}/properties")
    public Page<PropertyResource> getAllPropertiesByLandLordId(
            @PathVariable(name = "landLordId") Long landLordId,
            Pageable pageable
    ) {
        Page<Property> properties = propertyService.getAllPropertiesByLandLordId(landLordId, pageable);
        List<PropertyResource> resources = properties.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Update Property of a Land Lord",
            description = "Update A Property of a Land Lord",
            tags = "landlords")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Property updated",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/landlords/{landLordId}/properties/{propertyId}")
    public PropertyResource updateProperty(
            @PathVariable(name = "landLordId") Long landLordId,
            @PathVariable(name = "propertyId") Long propertyId,
            @Valid @RequestBody SavePropertyResource resource) {
        Property property = convertToEntity(resource);
        return convertToResource(propertyService.updateProperty(landLordId, propertyId, resource.getPlace(), property));

    }

    private Property convertToEntity(SavePropertyResource resource) {
        return mapper.map(resource, Property.class);
    }

    private PropertyResource convertToResource(Property entity) {
        return mapper.map(entity, PropertyResource.class);
    }


}
