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
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PropertiesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PropertyService propertyService;

    @Operation(summary = "Get All Properties",
            description = "Get All Properties by Page",
            tags = "properties")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All properties returned",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/properties")
    public Page<PropertyResource> getAllProperties(Pageable pageable) {
        Page<Property> propertyPage = propertyService.getAllProperties(pageable);
        List<PropertyResource> resources = propertyPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get All Active Properties",
            description = "Get All Active Properties by Page",
            tags = "properties")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All active properties returned",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/properties/active={active}")
    public Page<PropertyResource> getAllActiveProperties(@PathVariable(name = "active")Boolean active, Pageable pageable) {
        Page<Property> propertyPage = propertyService.getAllActiveProperties(active, pageable);
        List<PropertyResource> resources = propertyPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Property By Id",
            description = "Get A Property given a Id",
            tags = "properties")
    @GetMapping("/properties/{propertyId}")
    public PropertyResource getPropertyById(@PathVariable(name = "propertyId") Long propertyId) {
        return convertToResource(propertyService.getPropertyById(propertyId));
    }

    @Operation(summary = "Get Properties By Service Id",
            description = "Get All Properties given a Service Id",
            tags = "services")
    @GetMapping("/services/{serviceId}/properties")
    public Page<PropertyResource> getAllPropertiesByServiceID(@PathVariable(name = "serviceId") Long serviceId, Pageable pageable) {
        Page<Property> propertyPage = propertyService.getAllPropertiesByServiceId(serviceId, pageable);
        List<PropertyResource> resources = propertyPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }



    private Property convertToEntity(SavePropertyResource resource) {
        return mapper.map(resource, Property.class);
    }

    private PropertyResource convertToResource(Property entity) {
        return mapper.map(entity, PropertyResource.class);
    }


    @PostConstruct
    public void init() {
        mapper.addMappings(new PropertyMap<Property, PropertyResource>() {
            @Override
            protected void configure() {
                map().setLandLordId(source.getLandLord().getId());
                map().setLandLordFirstName(source.getLandLord().getFirstName());
                map().setLandLordLastName(source.getLandLord().getLastName());
                map().setDistrictId(source.getDistrict().getId());
                map().setProvinceId(source.getDistrict().getProvince().getId());
                map().setRegionId(source.getDistrict().getProvince().getRegion().getId());
                map().setDistrictName(source.getDistrict().getName());
                map().setProvinceName(source.getDistrict().getProvince().getName());
                map().setRegionName(source.getDistrict().getProvince().getRegion().getName());

            }
        });
    }

}
