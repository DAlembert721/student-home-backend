package com.astra.studenthomebackend.properties.services;


import com.astra.studenthomebackend.properties.domain.models.Contract;
import com.astra.studenthomebackend.properties.domain.models.Payment;
import com.astra.studenthomebackend.properties.domain.repositories.ContractRepository;
import com.astra.studenthomebackend.properties.domain.repositories.PaymentRepository;
import com.astra.studenthomebackend.properties.domain.services.PaymentService;
import com.astra.studenthomebackend.shared.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public Payment createPayment(Long contractId, Payment payment) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Contract", "Id", contractId));
        payment.setContract(contract);
        payment.setChecked(false);
        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment", "Id", paymentId));
    }

    @Override
    public Payment updatePayment(Long paymentId, Payment resource) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment", "Id", paymentId));
        payment.setChecked(payment.getChecked());
        payment.setComment(resource.getComment());
        payment.setImage(resource.getImage());
        return paymentRepository.save(payment);
    }

    @Override
    public Payment checkPayment(Long paymentId, Boolean check) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Payment", "Id", paymentId));
        payment.setChecked(check);
        return paymentRepository.save(payment);
    }

    @Override
    public ResponseEntity<?> deletePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Payment", "Id", paymentId));
        paymentRepository.delete(payment);
        return ResponseEntity.ok().build();
    }

    @Override
    public Page<Payment> getAllPaymentsByContractId(Long contractId, Pageable pageable) {
        if (!contractRepository.existsById(contractId))
            throw new ResourceNotFoundException("Contract", "Id", contractId);
        return paymentRepository.findByContractId(contractId, pageable);

    }


}
