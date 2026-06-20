package com.app.payment.service;

import com.app.payment.model.Payment;
import com.app.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        payment.setStatus("PAID");
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getByEmployeeId(Long employeeId) {
        return paymentRepository.findByEmployeeId(employeeId);
    }
}