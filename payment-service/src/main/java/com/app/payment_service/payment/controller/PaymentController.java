package com.app.payment.controller;

import com.app.payment.model.Payment;
import com.app.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public Payment createPayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/employee/{employeeId}")
    public List<Payment> getByEmployeeId(@PathVariable Long employeeId) {
        return paymentService.getByEmployeeId(employeeId);
    }
}