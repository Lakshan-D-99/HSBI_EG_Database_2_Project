package com.sp.hsbiegapi.controllers.emploControllers;

import com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos.PaymentRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.PaymentResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.PaymentResponseDaoUpdated;
import com.sp.hsbiegapi.services.emploServices.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/payments")
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

    // Get all the Payments with the Employee names
    @GetMapping("/all-with-name")
    public List<PaymentResponseDaoUpdated> getAllPaymentsWithEmpName(){
        return paymentService.getAllPaymentsWithEmpName();
    }

    // Add a new Payment to an Employee
    @PostMapping("/empId={employeeId}/new")
    public String addPaymentToEmployee(@PathVariable long employeeId,@RequestBody PaymentRequestDao paymentRequestDao){
        paymentService.addNewPaymentToEmployee(employeeId,paymentRequestDao);
        return "A Payment has added to the Employee with the Id: " + employeeId;
    }

}
