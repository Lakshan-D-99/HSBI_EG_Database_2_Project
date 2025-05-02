package com.sp.hsbiegapi.services.serviceimpl;

import com.sp.hsbiegapi.daos.RequestDaos.EmployeeRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.EmployeeResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.PaymentResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.QualificationResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.TimeRecordResponses.TimeRecordResponseDao;
import com.sp.hsbiegapi.models.emploModels.Employee;
import com.sp.hsbiegapi.models.emploModels.Payment;
import com.sp.hsbiegapi.models.emploModels.Qualification;
import com.sp.hsbiegapi.models.emploModels.TimeRecord;
import com.sp.hsbiegapi.repositories.EmployeeRepository;
import com.sp.hsbiegapi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //--------------------  Helper Methods  --------------------//

    // Convert an EmployeeRequestDao into an Employee Object
    private Employee conDaoToEntity(EmployeeRequestDao employeeRequestDao) {
        Employee employee = new Employee();
        employee.setEmpName(employeeRequestDao.getEmpName());
        employee.setEmpEmail(employeeRequestDao.getEmpEmail());
        employee.setEmpContactNum(employeeRequestDao.getEmpContactNumber());
        employee.setEmpPosition(employeeRequestDao.getEmpPosition());
        //employee.setEmpStartDate(employeeRequestDao.getEmpStartDate());
        employee.setEmpInfo(employeeRequestDao.getEmpInfo());
        return employee;
    }

    // Convert an Employee Object into an EmployeeResponseDao,as we are sending them to the Frontend
    private EmployeeResponseDao conEntityToDao(Employee employee){
        EmployeeResponseDao employeeResponseDao = new EmployeeResponseDao();
        employeeResponseDao.setEmpId(employee.getId());
        employeeResponseDao.setEmpName(employee.getEmpName());
        employeeResponseDao.setEmpEmail(employee.getEmpEmail());
        employeeResponseDao.setEmpContactNumber(employee.getEmpContactNum());
        employeeResponseDao.setEmpPosition(employee.getEmpPosition());
        employeeResponseDao.setEmpStartDate(employee.getEmpStartDate());

        // Check if the Employee has Payments and assign them to the Employee Response Object
        List<PaymentResponseDao> paymentResponseDaoList = new ArrayList<>();

        if (employee.getPaymentList() != null){
            for (Payment payment : employee.getPaymentList()){
                paymentResponseDaoList.add(conEntityToDao(payment));
            }
        }

        employeeResponseDao.setPaymentResponseDaoList(paymentResponseDaoList);

        // Check it the Employee has Qualifications and assign them to the Employee Response Object
        List<QualificationResponseDao> qualificationResponseDaoList = new ArrayList<>();

        if (employee.getQualificationList() != null){
            for (Qualification qualification : employee.getQualificationList()){
                qualificationResponseDaoList.add(conEntityToDao(qualification));
            }
        }

        employeeResponseDao.setQualificationResponseDaoList(qualificationResponseDaoList);

        // Check if the Employee has time keeping and assign them to the Employee Response Object
        List<TimeRecordResponseDao> timeRecordResponseDaoList = new ArrayList<>();

        if (employee.getQualificationList() != null){
            for (TimeRecord timeRecord : employee.getTimeRecordList()){
                timeRecordResponseDaoList.add(conEntityToDao(timeRecord));
            }
        }

        employeeResponseDao.setTimeRecordResponseDaoList(timeRecordResponseDaoList);

        return employeeResponseDao;
    }

    /*
    One Employee can have many Payments, Qualifications and Timekeeping, therefor we need to put them
    in the Employee response, but first we need to get them and convert them into the corresponding
    Objects.
    So again, we have to create Methods, that can convert Entities into DAOs.
     */

    // Convert Payment Entity into a Payment ResponseDao
    private PaymentResponseDao conEntityToDao(Payment payment){
        PaymentResponseDao paymentResponseDao = new PaymentResponseDao();
        paymentResponseDao.setId(payment.getId());
        paymentResponseDao.setPaymentAmount(payment.getPaymentAmount());
        paymentResponseDao.setPaymentType(payment.getPaymentType());
        paymentResponseDao.setPaymentInvoiceNum(payment.getPaymentInvoiceNum());
        paymentResponseDao.setPaymentDate(payment.getPaymentDate());
        paymentResponseDao.setEmpId(payment.getEmployee().getId());
        return paymentResponseDao;
    }

    // Convert a Qualification Entity into a Qualification Response Dao
    private QualificationResponseDao conEntityToDao(Qualification qualification){
        QualificationResponseDao qualificationResponseDao = new QualificationResponseDao();
        qualificationResponseDao.setId(qualification.getId());
        qualificationResponseDao.setQualificationType(qualification.getQualificationType());
        qualificationResponseDao.setQualificationValidDate(qualification.getQualificationValid());
        qualificationResponseDao.setEmpId(qualification.getEmployee().getId());
        return qualificationResponseDao;
    }

    // Convert Time Keeping Entity into a Time Keeping Response Dao
    private TimeRecordResponseDao conEntityToDao(TimeRecord timeRecord){
        TimeRecordResponseDao timeRecordResponseDao = new TimeRecordResponseDao();
        timeRecordResponseDao.setId(timeRecord.getId());
        timeRecordResponseDao.setWorkHours(timeRecord.getWorkHours());
        timeRecordResponseDao.setWorkDay(timeRecord.getWorkDay());
        timeRecordResponseDao.setJob(timeRecord.getJob());
        timeRecordResponseDao.setEmpId(timeRecord.getEmployee().getId());
        return timeRecordResponseDao;
    }


    @Override
    public List<EmployeeResponseDao> getAllEmployees() {

        try {

            // Get all the Employees from the Database
            List<Employee> allEmployees = employeeRepository.findAll();

            List<EmployeeResponseDao> employeeResponseDaoList = new ArrayList<>();

            // If there are Employees, convert them to Employee Response Objects and send the to the Front end
            if (!allEmployees.isEmpty()){

                for (Employee employee : allEmployees){
                    employeeResponseDaoList.add(conEntityToDao(employee));
                }
            }

            return employeeResponseDaoList;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null; // This is not allowed
        }
    }

    @Override
    public EmployeeResponseDao getEmployee(long employeeId) {
        try {

            // Get the Employee based on the passed in employee id
            Optional<Employee> employee = employeeRepository.findById(employeeId);

            // Check if the Employee exists and send it to the Front end
            if (employee.isPresent()){
                return conEntityToDao(employee.get());
            }

            System.out.println("Employee with the Id: " + employeeId + " does not exists");
            return new EmployeeResponseDao();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return null; // This is also not allowed
        }
    }

    @Override
    public void addNewEmployee(EmployeeRequestDao employeeRequestDao) {

        try {

            // Check if the Data is correctly passed into create an Employee
            if (employeeRequestDao.getEmpName() != null &&
                    employeeRequestDao.getEmpEmail() != null &&
                    employeeRequestDao.getEmpContactNumber() != null &&
                    employeeRequestDao.getEmpPosition() != null &&
                    employeeRequestDao.getEmpStartDate() != null &&
                    employeeRequestDao.getEmpInfo() != null) {


                // Create an Employee Object from the Request Dao and store it into the Database
                Employee employee = new Employee();
                employee.setEmpName(employeeRequestDao.getEmpName());
                employee.setEmpEmail(employeeRequestDao.getEmpEmail());
                employee.setEmpContactNum(employeeRequestDao.getEmpContactNumber());
                employee.setEmpPosition(employeeRequestDao.getEmpPosition());
                employee.setEmpInfo(employeeRequestDao.getEmpInfo());
                employee.setEmpStartDate(LocalDate.parse(employeeRequestDao.getEmpStartDate().toString()));
                employeeRepository.save(employee);

                System.out.println("New Employee has been created and stored it into the Database");


            } else {
                System.out.println("Error creating an Employee");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateEmployee(long employeeId, EmployeeRequestDao employeeRequestDao) {

        try {

            // Get the Employee from the Database based on the passed in id
            Optional<Employee> employee = employeeRepository.findById(employeeId);

            // Check if the Employee exists or not
            if (employee.isPresent()){

                // Update the Employee Data and save the Employee Object into the Database
                Employee existEmployee = employee.get();

                existEmployee.setId(employeeId);
                existEmployee.setEmpName(employeeRequestDao.getEmpName());
                existEmployee.setEmpEmail(employeeRequestDao.getEmpEmail());
                existEmployee.setEmpContactNum(employeeRequestDao.getEmpContactNumber());
                existEmployee.setEmpPosition(employeeRequestDao.getEmpPosition());
                existEmployee.setEmpStartDate(employeeRequestDao.getEmpStartDate());
                existEmployee.setEmpInfo(employeeRequestDao.getEmpInfo());

                employeeRepository.save(existEmployee);

                System.out.println("Employee with the Employee Id: " + employeeId + " has been updated");
            }

            System.out.println("This Employee can not be updated, beacuse it doe not exists in the Database");

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void deleteEmployee(long employeeId) {

      try {

          // Get the Employee based on the passed in id
          Optional<Employee> employee = employeeRepository.findById(employeeId);

          // Check if the Employee exists with the given id and delete the Employee
          if (employee.isPresent()){
              employeeRepository.delete(employee.get());
              System.out.println("EMPLOYEE SERVICE IMPL - employee has been deleted");
          }

      }catch (Exception e){
          System.out.println(e.getMessage());
      }
    }
}
