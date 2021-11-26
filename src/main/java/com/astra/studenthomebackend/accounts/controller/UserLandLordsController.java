package com.astra.studenthomebackend.accounts.controller;


import com.astra.studenthomebackend.accounts.domain.model.LandLord;
import com.astra.studenthomebackend.accounts.domain.service.LandLordService;
import com.astra.studenthomebackend.accounts.resource.LandLordResource;
import com.astra.studenthomebackend.accounts.resource.SaveLandLordResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserLandLordsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private LandLordService landLordService;

    @Operation(summary = "Get LandLord For A User",
            description = "Get a landlord of a User",
            tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All accounts of a user returned",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/{userId}/landlords/{landLordId}")
    public LandLordResource getLandlordByIdAndUserId(@PathVariable(name = "landLordId") Long landLordId, @PathVariable(name = "userId") Long userId) {
        return convertToResource(landLordService.getLandLordByIdAndUserId(landLordId, userId));
    }

    @Operation(summary = "Post LandLord For A User",
            description = "Create a LandLord",
            tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "LandLord created",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/users/{userId}/landlords")
    public LandLordResource createLandlord(@PathVariable Long userId, @Valid @RequestBody SaveLandLordResource resource) {
        LandLord landLord = convertToEntity(resource);
        return convertToResource(landLordService.createLandLord(userId, resource.getSubscriptionId(), landLord));
    }

    @Operation(summary = "Put LandLord Of A User",
            description = "Update a land lord of a user",
            tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "LandLord updated",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/users/{userId}/landlords/{landLordId}")
    public LandLordResource updateLandlord(@PathVariable(name = "userId") Long userId, @PathVariable(name = "landLordId") Long landLordId, @Valid @RequestBody SaveLandLordResource resource){
        LandLord landLord = convertToEntity(resource);
        return convertToResource(landLordService.updateLandLord(userId, landLordId, landLord));
    }

    @Operation(summary = "Delete LandLord Of A User",
            description = "Delete a land lord of a user",
            tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Land lord deleted",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/users/{userId}/landlords/{landLordId}")
    public ResponseEntity<?> deleteResponse(@PathVariable(name = "userId") Long userId, @PathVariable(name="landLordId") Long landLordId) {
        return landLordService.deleteLandLord(userId, landLordId);
    }

    private LandLord convertToEntity(SaveLandLordResource resource) {
        return mapper.map(resource, LandLord.class);
    }

    private LandLordResource convertToResource(LandLord entity) {
        return mapper.map(entity, LandLordResource.class);
    }

    @PostConstruct
    public void init() {
        mapper.addMappings(new PropertyMap<LandLord, LandLordResource>() {
            @Override
            protected void configure() {
                map().setSubscriptionName(source.getSubscription().getName());
                map().setEmail(source.getUser().getUsername());
            }
        });
    }

}
