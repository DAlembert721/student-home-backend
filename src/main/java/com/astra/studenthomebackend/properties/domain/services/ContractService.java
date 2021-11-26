package com.astra.studenthomebackend.properties.domain.services;


import com.astra.studenthomebackend.properties.domain.models.Contract;
import com.astra.studenthomebackend.properties.domain.models.EContractStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface ContractService {
    Contract createContract(Long studentId, Long propertyId, Contract contract);
    Contract getContractById(Long contractId);
    Page<Contract> getAllContractsByPropertyId(Long propertyId, Pageable pageable);
    Page<Contract> getAllContractsByStudentId(Long studentId, Pageable pageable);
    Contract updateContract(Long contractId, Contract resource);
    ResponseEntity<?> deleteContract(Long contractId);
    Contract updateStateOfContract(Long contractId, EContractStatus state);
}
