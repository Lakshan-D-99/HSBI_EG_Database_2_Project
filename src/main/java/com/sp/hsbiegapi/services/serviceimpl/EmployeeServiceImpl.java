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
import com.sp.hsbiegapi.utils.Mapper;
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


    @Override
    public List<EmployeeResponseDao> getAllEmployees() {

        try {

            // Get all the Employees from the Database
            List<Employee> allEmployees = employeeRepository.findAll();

            List<EmployeeResponseDao> employeeResponseDaoList = new ArrayList<>();

            // If there are Employees, convert them to Employee Response Objects and send the to the Front end
            if (!allEmployees.isEmpty()){

                for (Employee employee : allEmployees){
                    employeeResponseDaoList.add(Mapper.conEntityToDao(employee));
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
                return Mapper.conEntityToDao(employee.get());
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
