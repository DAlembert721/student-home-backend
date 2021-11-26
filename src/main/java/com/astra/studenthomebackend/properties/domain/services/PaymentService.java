package com.astra.studenthomebackend.properties.domain.services;


import com.astra.studenthomebackend.properties.domain.models.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    Payment createPayment(Long contractId, Payment payment);
    Payment getPaymentById(Long paymentId);
    Payment updatePayment(Long paymentId, Payment resource);
    Payment checkPayment(Long paymentId, Boolean check);
    ResponseEntity<?> deletePayment(Long paymentId);
    Page<Payment> getAllPaymentsByContractId(Long contractId, Pageable pageable);
}
