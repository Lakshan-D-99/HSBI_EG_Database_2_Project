package com.sp.hsbiegapi.services.serviceimpl;

import com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos.PaymentRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.PaymentResponseDao;
import com.sp.hsbiegapi.models.emploModels.Employee;
import com.sp.hsbiegapi.models.emploModels.Payment;
import com.sp.hsbiegapi.repositories.emploRepositories.EmployeeRepository;
import com.sp.hsbiegapi.repositories.emploRepositories.PaymentRepository;
import com.sp.hsbiegapi.services.emploServices.PaymentService;
import com.sp.hsbiegapi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, EmployeeRepository employeeRepository) {
        this.paymentRepository = paymentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<PaymentResponseDao> getAllPaymentsOfEmployee(long employeeId) {
        try {

            List<Payment> allPayments = paymentRepository.findAll();

            List<Payment> filterdPayments = allPayments.stream().filter(payment -> payment.getEmployee().getId()==employeeId).toList();

            List<PaymentResponseDao> paymentResponseDaos = filterdPayments.stream().map(Mapper::conEntityToDao).toList();

            return paymentResponseDaos;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public PaymentResponseDao getSingleEmployeePayment(long paymentId) {
        try {
            // Get the Payment Object from the Database
            Optional<Payment> payment = paymentRepository.findById(paymentId);

            // Check if the Payment Object exists and send it to the front end
            if (payment.isPresent()) {
                return Mapper.conEntityToDao(payment.get());
            }

            System.out.println("This Payment information does not exists");
            return new PaymentResponseDao();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void addNewPaymentToEmployee(long employeeId, PaymentRequestDao paymentRequestDao) {

        try {
            // Check if the Employee exists in the Database
            Optional<Employee> employee = employeeRepository.findById(employeeId);

            if (employee.isPresent()) {

                // Check if all the required Fields are available
                if (paymentRequestDao.getPaymentAmount() != 0.0 &&
                        paymentRequestDao.getPaymentType() != null &&
                        paymentRequestDao.getPaymentInvoiceNum() != null &&
                        paymentRequestDao.getPaymentDate() != null) {

                    Payment payment = new Payment();
                    payment.setPaymentAmount(paymentRequestDao.getPaymentAmount());
                    payment.setPaymentType(paymentRequestDao.getPaymentType());
                    payment.setPaymentInvoiceNum(paymentRequestDao.getPaymentInvoiceNum());
                    payment.setPaymentDate(paymentRequestDao.getPaymentDate());
                    payment.setEmployee(employee.get());

                    paymentRepository.save(payment);

                } else {
                    System.out.println("Required fields can not be null");
                }

            } else {
                System.out.println("Employee with the ID: " + employeeId + " does not exists");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void updateEmployeePayment(long employeeId, PaymentRequestDao paymentRequestDao) {

    }

    @Override
    public void deleteEmployeePayment(long paymentId) {
        try {
            // Get the Payment object from the Database
            Optional<Payment> payment = paymentRepository.findById(paymentId);

            // Check if the Payment exists and delete the Payment
            if (payment.isPresent()) {
                paymentRepository.delete(payment.get());
                System.out.println("Payment has been deleted");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // <!-- Intern Methods -->

    @Override
    public Payment getLatestEmployeePayment(long employeeId) {

        try {

            // Find and Check if the Employee exists in the the Database
            Optional<Employee> employee = employeeRepository.findById(employeeId);

            if (employee.isPresent()) {

                // Get and Check if the latest Payment details of the Employee exists or not
                //Optional<Payment> payment = paymentRepository.getEmployeeLastPayment(employeeId);


                System.out.println("This Employee does not have a latest Payment Date");
                return new Payment();

            }
            System.out.println("This Employee does not have a latest Payment Date");
            return new Payment();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
