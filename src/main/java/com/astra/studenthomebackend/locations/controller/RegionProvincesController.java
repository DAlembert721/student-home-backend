package com.astra.studenthomebackend.locations.controller;

import com.astra.studenthomebackend.locations.domain.model.Province;
import com.astra.studenthomebackend.locations.domain.service.LocationService;
import com.astra.studenthomebackend.locations.resource.ProvinceResource;
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

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/location")
public class RegionProvincesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private LocationService locationService;

    @Operation(summary = "Get Provinces of a Region",
            description = "Get All Provinces of a Region by Page",
            tags = "regions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All provinces of a region returned",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/regions/{regionId}/provinces")
    public Page<ProvinceResource> getAllProvincesByRegionId(@PathVariable Long regionId, Pageable pageable) {
        Page<Province> provincePage = locationService.getAllProvincesByRegionId(regionId, pageable);
        List<ProvinceResource> resources = provincePage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private ProvinceResource convertToResource(Province entity) {
        return mapper.map(entity, ProvinceResource.class);
    }

}