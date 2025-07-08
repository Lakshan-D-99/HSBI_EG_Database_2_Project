package com.sp.hsbiegapi.services.emploServices;

import com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos.PaymentRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.PaymentResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.PaymentResponseDaoUpdated;

import java.util.List;

public interface PaymentService {

    // Get all the Payments of an Employee
    List<PaymentResponseDao> getAllPaymentsOfEmployee(long employeeId);

    // Get a single Payment Detail
    PaymentResponseDao getSingleEmployeePayment(long paymentId);

    // Get all the Payments with the Employee Name
    List<PaymentResponseDaoUpdated> getAllPaymentsWithEmpName();

    // Add a new Payment to an Employee
    void addNewPaymentToEmployee(long employeeId, PaymentRequestDao paymentRequestDao);

}
