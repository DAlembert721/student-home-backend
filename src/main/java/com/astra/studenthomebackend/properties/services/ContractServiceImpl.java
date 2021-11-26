package com.astra.studenthomebackend.properties.services;


import com.astra.studenthomebackend.accounts.domain.model.Student;
import com.astra.studenthomebackend.accounts.domain.repository.StudentRepository;
import com.astra.studenthomebackend.properties.domain.models.Contract;
import com.astra.studenthomebackend.properties.domain.models.EContractStatus;
import com.astra.studenthomebackend.properties.domain.models.Property;
import com.astra.studenthomebackend.properties.domain.repositories.ContractRepository;
import com.astra.studenthomebackend.properties.domain.repositories.PropertyRepository;
import com.astra.studenthomebackend.properties.domain.services.ContractService;
import com.astra.studenthomebackend.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private ContractRepository contractRepository;

    @Override
    public Contract createContract(Long studentId, Long propertyId, Contract contract) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student", "Id", studentId));
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Property", "Id", propertyId));
        contract.setStudent(student);
        contract.setProperty(property);
        contract.setState(EContractStatus.UNRESOLVED);
        return  contractRepository.save(contract);
    }

    @Override
    public Contract getContractById(Long contractId) {
        return contractRepository.findById(contractId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Contract", "Id", contractId));
    }

    @Override
    public Page<Contract> getAllContractsByPropertyId(Long propertyId, Pageable pageable) {
        if(!propertyRepository.existsById(propertyId))
            throw new ResourceNotFoundException("Property", "Id", propertyId);
        return contractRepository.findByPropertyId(propertyId, pageable);

    }

    @Override
    public Page<Contract> getAllContractsByStudentId(Long studentId, Pageable pageable) {
        if(!studentRepository.existsById(studentId))
            throw new ResourceNotFoundException("Student", "Id", studentId);
        return contractRepository.findByStudentId(studentId, pageable);
    }

    @Override
    public Contract updateContract(Long contractId, Contract resource) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contract", "Id", contractId));
        contract.setAmount(resource.getAmount());
        contract.setState(resource.getState());
        contract.setDescription(resource.getDescription());
        return contractRepository.save(contract);
    }

    @Override
    public ResponseEntity<?> deleteContract(Long contractId) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contract", "Id", contractId));
        contractRepository.delete(contract);
        return ResponseEntity.ok().build();
    }

    @Override
    public Contract updateStateOfContract(Long contractId, EContractStatus state) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contract", "Id", contractId));
        contract.setState(state);
        return contractRepository.save(contract);
    }
}
