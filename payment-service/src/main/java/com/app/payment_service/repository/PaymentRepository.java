package com.app.payment_service.repository;

import com.app.payment_service.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByEmployeeId(Long employeeId);
}