package com.astra.studenthomebackend.properties.controller;


import com.astra.studenthomebackend.properties.domain.models.Request;
import com.astra.studenthomebackend.properties.domain.services.RequestService;
import com.astra.studenthomebackend.properties.resource.RequestResource;
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
public class StudentRequestsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RequestService requestService;

    @Operation(summary = "Get All Requests By StudentId", description = "Get all requests for a property given a studentId", tags = {"students"})
    @GetMapping("/students/{studentId}/requests")
    public Page<RequestResource> getAllRequestsByStudentId(
            @PathVariable(name = "studentId") Long studentId,
            Pageable pageable) {
        Page<Request> requests = requestService.getAllRequestsByStudentIdId(studentId, pageable);
        List<RequestResource> requestResources = requests.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(requestResources, pageable, requestResources.size());
    }

    private RequestResource convertToResource(Request entity) {
        return mapper.map(entity, RequestResource.class);
    }
}
