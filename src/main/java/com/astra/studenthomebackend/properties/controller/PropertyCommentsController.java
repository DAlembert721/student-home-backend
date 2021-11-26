package com.astra.studenthomebackend.properties.controller;


import com.astra.studenthomebackend.properties.domain.models.PropertyComment;
import com.astra.studenthomebackend.properties.domain.services.PropertyCommentService;
import com.astra.studenthomebackend.properties.resource.PropertyCommentResource;
import com.astra.studenthomebackend.properties.resource.SavePropertyCommentResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PropertyCommentsController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PropertyCommentService propertyCommentService;

    @Operation(summary = "Create PropertyComment", description = "Create a new comment for a property", tags = {"students"})
    @PostMapping("/students/{studentId}/properties/{propertyId}/comments")
    public PropertyCommentResource createComment(@PathVariable(name = "studentId") Long studentId,
                                                 @PathVariable(name = "propertyId") Long propertyId,
                                                 @RequestBody @Valid SavePropertyCommentResource resource){
        PropertyComment comment = convertToEntity(resource);
        return convertToResource(propertyCommentService.createPropertyComment(studentId, propertyId, comment));
    }

    @Operation(summary = "Get All PropertyComments By StudentId", description = "Get all comments for a property given a studentId", tags = {"students"})
    @GetMapping("/students/{studentId}/comments")
    public Page<PropertyCommentResource> getAllPropertyCommentsByStudentId(@PathVariable(name = "studentId") Long studentId, Pageable pageable){
        Page<PropertyComment> commentPage = propertyCommentService.getAllPropertyCommentsByStudentId(studentId, pageable);
        List<PropertyCommentResource> commentResources = commentPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(commentResources, pageable, commentResources.size());
    }

    @Operation(summary = "Get All PropertyComments By PropertyId", description = "Get all comments for a property given a PropertyId", tags = {"properties"})
    @GetMapping("/properties/{propertyId}/comments")
    public Page<PropertyCommentResource> getAllPropertyCommentsByPropertyId(
            @PathVariable(name = "propertyId") Long propertyId,
            Pageable pageable){
        Page<PropertyComment> commentPage = propertyCommentService.getAllPropertyCommentByPropertyId(propertyId, pageable);
        List<PropertyCommentResource> commentResources = commentPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(commentResources, pageable, commentResources.size());
    }

    @Operation(summary = "Update PropertyComment", description = "Update a comment", tags = {"comments"})
    @PutMapping("/comments/{commentId}")
    public PropertyCommentResource updateComment(@PathVariable(name = "commentId") Long commentId,
                                                 @RequestBody @Valid SavePropertyCommentResource resource){
        PropertyComment comment = convertToEntity(resource);
        return convertToResource(propertyCommentService.updatePropertyComment(commentId, comment));
    }

    @Operation(summary = "Delete PropertyComment", description = "Delete a comment", tags = {"comments"})
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "commentId") Long commentId){
        return propertyCommentService.deletePropertyComment(commentId);
    }



    private PropertyComment convertToEntity(SavePropertyCommentResource resource) {
        return mapper.map(resource, PropertyComment.class);
    }

    private PropertyCommentResource convertToResource(PropertyComment entity) {
        return mapper.map(entity, PropertyCommentResource.class);
    }

    @PostConstruct
    public void init() {
        mapper.addMappings(new PropertyMap<PropertyComment, PropertyCommentResource>() {
            @Override
            protected void configure() {
                map().setStudentFirstName(source.getStudent().getFirstName());
                map().setStudentLastName(source.getStudent().getLastName());
                map().setStudentImage(source.getStudent().getImage());
                map().setStudentId(source.getStudent().getId());
                
            }
        });
    }
}
