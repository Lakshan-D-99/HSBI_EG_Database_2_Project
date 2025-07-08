package com.sp.hsbiegapi.services.serviceimpl.emploServiceImpls;

import com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos.PaymentRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.PaymentResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.PaymentResponseDaoUpdated;
import com.sp.hsbiegapi.models.emploModels.Employee;
import com.sp.hsbiegapi.models.emploModels.Payment;
import com.sp.hsbiegapi.repositories.emploRepositories.EmployeeRepository;
import com.sp.hsbiegapi.repositories.emploRepositories.PaymentRepository;
import com.sp.hsbiegapi.services.emploServices.PaymentService;
import com.sp.hsbiegapi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

            // Get the Employee based on the passed in Employee id
            Employee employee = employeeRepository.findById(employeeId).orElseThrow();

            List<PaymentResponseDao> paymentResponseDAOs = new ArrayList<>();

            employee.getPaymentList().forEach(payment -> paymentResponseDAOs.add(Mapper.conEntityToDao(payment)));

            return paymentResponseDAOs;

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
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
    public List<PaymentResponseDaoUpdated> getAllPaymentsWithEmpName() {
        try {
            return paymentRepository.getPaymentWithEmployeeName();
        } catch (Exception e) {
            throw new RuntimeException(e);
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
}
