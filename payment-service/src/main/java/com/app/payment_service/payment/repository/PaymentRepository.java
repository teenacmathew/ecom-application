package com.app.payment.repository;

import com.app.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByEmployeeId(Long employeeId);
}