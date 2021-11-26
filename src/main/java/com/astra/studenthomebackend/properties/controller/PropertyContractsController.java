package com.astra.studenthomebackend.properties.controller;


import com.astra.studenthomebackend.properties.domain.models.Contract;
import com.astra.studenthomebackend.properties.domain.services.ContractService;
import com.astra.studenthomebackend.properties.resource.ContractResource;
import com.astra.studenthomebackend.properties.resource.SaveContractResource;
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
public class PropertyContractsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ContractService contractService;

    @Operation(summary = "Get All Contracts By PropertyId",
            description = "Get all contracts for a property given a PropertyId",
            tags = {"properties"})
    @GetMapping("/properties/{propertyId}/contracts")
    public Page<ContractResource> getAllContractsByPropertyId(
            @PathVariable(name = "propertyId") Long propertyId,
            Pageable pageable){
        Page<Contract> contractPage = contractService.getAllContractsByPropertyId(propertyId, pageable);
        List<ContractResource> contractResources = contractPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(contractResources, pageable, contractResources.size());
    }



    private Contract convertToEntity(SaveContractResource resource) {
        return mapper.map(resource, Contract.class);
    }

    private ContractResource convertToResource(Contract entity) {
        return mapper.map(entity, ContractResource.class);
    }
}
