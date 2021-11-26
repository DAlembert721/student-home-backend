package com.astra.studenthomebackend.accounts.controller;

import com.astra.studenthomebackend.accounts.domain.model.Account;
import com.astra.studenthomebackend.accounts.domain.service.AccountService;
import com.astra.studenthomebackend.accounts.resource.AccountResource;
import com.astra.studenthomebackend.accounts.resource.SaveAccountResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

public class UserAccountsController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AccountService accountService;

    @Operation(summary = "Get Accounts Of A User",
            description = "Get All Accounts Of A User",
            tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All accounts of a user returned",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/{userId}/accounts")
    public Page<AccountResource> getAllAccountsByUserId(@PathVariable(name = "userId") Long userId, Pageable pageable) {
        Page<Account> accountPage = accountService.getAllAccountsByUserId(userId, pageable);
        List<AccountResource> resources = accountPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Account Of A User",
            description = "Get a Account of a User",
            tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Account returned",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users/{userId}/accounts/{accountId}")
    public AccountResource getAccountByIdAndUserId(@PathVariable(name = "accountId") Long accountId, @PathVariable(name = "userId") Long userId) {
        return convertToResource(accountService.getAccountByIdAndUserId(accountId, userId));
    }

    @Operation(summary = "Post Account For A User",
            description = "Create a account for a user",
            tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Account created",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/users/{userId}/accounts")
    public AccountResource createAccount(@PathVariable(name = "userId") Long userId, @Valid @RequestBody SaveAccountResource resource) {
        Account account = convertToEntity(resource);
        return convertToResource(accountService.createAccount(userId, account));
    }

    @Operation(summary = "Put Account of a User",
            description = "Update a account of a user",
            tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Account updated",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/users/{userId}/accounts/{accountId}")
    public AccountResource updateAccount(@PathVariable(name = "userId") Long userId, @PathVariable(name = "accountId") Long accountId, @Valid @RequestBody SaveAccountResource resource) {
        Account account = convertToEntity(resource);
        return convertToResource(accountService.updateAccount(accountId, userId, account));
    }

    @Operation(summary = "Delete Account Of A User",
            description = "Delete a account of a user",
            tags = "users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Account deleted",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/users/{userId}/accounts/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable(name = "userId") Long userId, @PathVariable(name = "accountId") Long accountId) {

        return accountService.deleteAccount(accountId, userId);
    }


    private Account convertToEntity(SaveAccountResource resource) {

        return mapper.map(resource, Account.class);
    }

    private AccountResource convertToResource(Account entity) {

        return mapper.map(entity, AccountResource.class);
    }
}
