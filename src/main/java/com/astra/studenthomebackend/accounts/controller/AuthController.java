package com.astra.studenthomebackend.accounts.controller;

import com.astra.studenthomebackend.accounts.domain.model.auth.User;
import com.astra.studenthomebackend.accounts.domain.service.UserService;
import com.astra.studenthomebackend.accounts.resource.SaveUserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody @Valid SaveUserResource resource) {
        User user = userService.create(SaveUserResource.resourceToEntity(resource));
        if(user != null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
