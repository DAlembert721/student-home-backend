package com.astra.studenthomebackend.properties.controller;


import com.astra.studenthomebackend.properties.domain.models.Payment;
import com.astra.studenthomebackend.properties.domain.services.PaymentService;
import com.astra.studenthomebackend.properties.resource.PaymentResource;
import com.astra.studenthomebackend.properties.resource.SavePaymentResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class PaymentsController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PaymentService paymentService;

    @Operation(summary = "Get Payment By Payment Id",
            description = "Get A Payment given a Id",
            tags = "payments")
    @GetMapping("/payments/{paymentId}")
    public PaymentResource getAllPaymentsByPropertyId(@PathVariable(name = "paymentId") Long paymentId) {
        return convertToResource(paymentService.getPaymentById(paymentId));
    }

    @Operation(summary = "Post Payment",
            description = "Post A New Payment",
            tags = "contracts")
    @PostMapping("/contracts/{contractId}/payments")
    public PaymentResource createPayment(
            @PathVariable(name = "contractId") Long contractId,
            @RequestBody @Valid SavePaymentResource paymentResource) {
        Payment payment = convertToEntity(paymentResource);
        return convertToResource(paymentService.createPayment(contractId, payment));
    }

    @Operation(summary = "Update Payment By Payment Id",
            description = "Update A Payment given a Id",
            tags = "payments")
    @PutMapping("/payments/{paymentId}")
    public PaymentResource updatePayment(
            @PathVariable(name = "paymentId") Long paymentId,
            @RequestBody @Valid SavePaymentResource paymentResource) {
        Payment payment = convertToEntity(paymentResource);
        return convertToResource(paymentService.updatePayment(paymentId, payment));
    }

    @Operation(summary = "Update Check Payment By Payment Id",
            description = "Check A Payment given a Id",
            tags = "payments")
    @PutMapping("/payments/{paymentId}/check={check}")
    public PaymentResource updateCheckPayment(
            @PathVariable(name = "paymentId") Long paymentId,
            @PathVariable(name = "check") Boolean check) {
        return convertToResource(paymentService.checkPayment(paymentId, check));
    }

    @Operation(summary = "Delete Payment By Payment Id",
            description = "Delete A Payment given a Id",
            tags = "payments")
    @DeleteMapping("/payments/{paymentId}")
    public ResponseEntity<?> deletePaymentById(@PathVariable(name = "paymentId") Long paymentId) {
        return paymentService.deletePayment(paymentId);
    }

    private Payment convertToEntity(SavePaymentResource resource) {
        return mapper.map(resource, Payment.class);
    }

    private PaymentResource convertToResource(Payment entity) {
        return mapper.map(entity, PaymentResource.class);
    }
}
