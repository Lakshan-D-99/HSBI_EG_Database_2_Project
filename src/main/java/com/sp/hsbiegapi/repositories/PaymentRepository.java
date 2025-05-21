package com.sp.hsbiegapi.repositories;

import com.sp.hsbiegapi.models.emploModels.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    @Query("SELECT p FROM Payment p WHERE p.employee.id = :employeeId")
    List<Payment> getAllEmployeePayments(long employeeId);

    /*@Query("SELECT P FROM Payment p WHERE p.employee.id=:employeeId ORDER BY p.paymentDate DESC LIMIT 1")
    Optional<Payment> getEmployeeLastPayment(long employeeId);*/

}
