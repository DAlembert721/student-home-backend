package com.astra.studenthomebackend.accounts.controller;


import com.astra.studenthomebackend.accounts.domain.model.auth.User;
import com.astra.studenthomebackend.accounts.domain.service.UserService;
import com.astra.studenthomebackend.accounts.resource.SaveUserResource;
import com.astra.studenthomebackend.accounts.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get User",
            description = "Get a user",
            tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User founded",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/{userId}")
    public UserResource getUserById(@PathVariable Long userId) {
        return convertToResource(userService.getUserById(userId));
    }

    @Operation(summary = "Post User",
            description = "Post a user",
            tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "User created",
                    content = @Content(mediaType = "application/json"))
    })


    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }
}
