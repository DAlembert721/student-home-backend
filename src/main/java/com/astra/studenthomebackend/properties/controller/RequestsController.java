package com.astra.studenthomebackend.properties.controller;


import com.astra.studenthomebackend.properties.domain.models.ERequestStatus;
import com.astra.studenthomebackend.properties.domain.models.Request;
import com.astra.studenthomebackend.properties.domain.services.RequestService;
import com.astra.studenthomebackend.properties.resource.RequestResource;
import com.astra.studenthomebackend.properties.resource.SaveRequestResource;
import io.swagger.v3.oas.annotations.Operation;
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
public class RequestsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RequestService requestService;

    @Operation(summary = "Create Request", description = "Create a new request", tags = {"students"})
    @PostMapping("/students/{studentId}/properties/{propertyId}/requests")
    public RequestResource createRequest(@PathVariable(name = "studentId") Long studentId,
                                         @PathVariable(name = "propertyId") Long propertyId,
                                         @RequestBody @Valid SaveRequestResource resource){
        Request request = convertToEntity(resource);
        return convertToResource(requestService.createRequest(studentId, propertyId, request));
    }


    @Operation(summary = "Update Request", description = "Update a Request", tags = {"requests"})
    @PutMapping("/requests/{requestId}")
    public RequestResource updateRequest(@PathVariable(name = "requestId") Long requestId,
                                                 @RequestBody @Valid SaveRequestResource resource){
        Request request = convertToEntity(resource);
        return convertToResource(requestService.updateRequest(requestId, request));
    }

    @Operation(summary = "Delete Request", description = "Delete a Request", tags = {"requests"})
    @DeleteMapping("/requests/{requestId}")
    public ResponseEntity<?> deleteRequest(@PathVariable(name = "requestId") Long requestId){
        return requestService.deleteRequest(requestId);
    }

    @Operation(summary = "Update Request State", description = "Update a Request State", tags = {"requests"})
    @PutMapping("/requests/{requestId}/state={state}")
    public RequestResource updateRequest(@PathVariable(name = "requestId") Long requestId,
                                         @PathVariable(name = "state") ERequestStatus state){
        return convertToResource(requestService.updateStateOfRequest(requestId, state));
    }

    private Request convertToEntity(SaveRequestResource resource) {
        return mapper.map(resource, Request.class);
    }

    private RequestResource convertToResource(Request entity) {
        return mapper.map(entity, RequestResource.class);
    }

    @PostConstruct
    public void init() {
        mapper.addMappings(new PropertyMap<Request, RequestResource>() {
            @Override
            protected void configure() {
                map().setFirstNameStudent(source.getStudent().getFirstName());
                map().setLastNameStudent(source.getStudent().getLastName());
                map().setFirstNameLandlord(source.getProperty().getLandLord().getFirstName());
                map().setLastNameLandlord(source.getProperty().getLandLord().getLastName());
            }
        });
    }
}
