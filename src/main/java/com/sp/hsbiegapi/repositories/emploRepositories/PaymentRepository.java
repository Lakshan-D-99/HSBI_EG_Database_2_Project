package com.sp.hsbiegapi.repositories.emploRepositories;

import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.PaymentResponseDaoUpdated;
import com.sp.hsbiegapi.models.emploModels.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {

    // Get All the Payments with their corresponding Employee name
    @Query(
            value = "select payment.id, payment.payment_amount, payment.payment_date, employee.emp_name from Payment payment inner join Employee employee on payment.employee_id = employee.id",
            nativeQuery = true
    )
    List<PaymentResponseDaoUpdated> getPaymentWithEmployeeName();

}
