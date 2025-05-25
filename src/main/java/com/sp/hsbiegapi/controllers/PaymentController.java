package com.sp.hsbiegapi.controllers;

import com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos.PaymentRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.PaymentResponseDao;
import com.sp.hsbiegapi.services.emploServices.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Get all the Payments of a specific Employee
    @GetMapping("/empId={employeeId}/payments/all")
    public List<PaymentResponseDao> getAllEmployeePayments(@PathVariable long employeeId){
        return paymentService.getAllPaymentsOfEmployee(employeeId);
    }

    // Get a Single Payment of an Employee
    @GetMapping("/paymentId={payId}")
    public PaymentResponseDao getSinglePayment(@PathVariable long payId){
        return paymentService.getSingleEmployeePayment(payId);
    }

    // Add a new Payment to an Employee
    @PostMapping("/empId={employeeId}/new")
    public String addPaymentToEmployee(@PathVariable long employeeId,@RequestBody PaymentRequestDao paymentRequestDao){
        paymentService.addNewPaymentToEmployee(employeeId,paymentRequestDao);
        return "A Payment has added to the Employee with the Id: " + employeeId;
    }

    // Delete a Payment from an existing Employee
    @DeleteMapping("/paymentId={payId}")
    public String deleteEmployeePayment(@PathVariable long payId){
        paymentService.deleteEmployeePayment(payId);
        return "Payment has been deleted";
    }
}
