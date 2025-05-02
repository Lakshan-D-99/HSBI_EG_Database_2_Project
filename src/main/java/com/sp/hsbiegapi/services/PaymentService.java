package com.sp.hsbiegapi.services;

import com.sp.hsbiegapi.daos.RequestDaos.PaymentRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.PaymentResponseDao;
import com.sp.hsbiegapi.models.emploModels.Payment;

import java.util.List;

public interface PaymentService {

    // Get all the Payments of an Employee
    List<PaymentResponseDao> getAllPaymentsOfEmployee(long employeeId);

    // Get a single Payment Detail
    PaymentResponseDao getSingleEmployeePayment(long paymentId);

    // Add a new Payment to an Employee
    void addNewPaymentToEmployee(long employeeId, PaymentRequestDao paymentRequestDao);

    // Update Payment Data <!-- This is Optional -->
    void updateEmployeePayment(long employeeId, PaymentRequestDao paymentRequestDao);

    // Delete a Payment
    void deleteEmployeePayment(long paymentId);

    // <!-- Intern Methods --> //

    // Get the Latest Payment details of a specific Employee
    Payment getLatestEmployeePayment(long employeeId);
}
