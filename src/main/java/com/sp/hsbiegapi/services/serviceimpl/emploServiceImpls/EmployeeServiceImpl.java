package com.sp.hsbiegapi.services.serviceimpl.emploServiceImpls;

import com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos.EmployeeRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.EmployeeResponseDao;
import com.sp.hsbiegapi.exceptions.employeeExceptions.EmployeeAlreadyExistsException;
import com.sp.hsbiegapi.exceptions.employeeExceptions.EmployeeNotFoundException;
import com.sp.hsbiegapi.exceptions.employeeExceptions.ErrorCreatingAnEmployeeException;
import com.sp.hsbiegapi.exceptions.employeeExceptions.MissingEmployeeDataException;
import com.sp.hsbiegapi.models.emploModels.Employee;
import com.sp.hsbiegapi.repositories.emploRepositories.EmployeeRepository;
import com.sp.hsbiegapi.services.emploServices.EmployeeService;
import com.sp.hsbiegapi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
            allEmployees.forEach(employee -> employeeResponseDaoList.add(Mapper.conEntityToDao(employee)));

            return employeeResponseDaoList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeResponseDao getEmployee(long employeeId) {
        try {

            // Get the Employee based on the passed in employee id
            Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                    ()-> new EmployeeNotFoundException("Error getting the Employee")
            );

            return Mapper.conEntityToDao(employee);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void addNewEmployee(EmployeeRequestDao employeeRequestDao) {

        try {

            // Check if the Employee has passed all the data to create a new Employee
            if (employeeRequestDao.getEmpName().isEmpty() && employeeRequestDao.getEmpEmail().isEmpty() && employeeRequestDao.getEmpContactNumber().isEmpty() && employeeRequestDao.getEmpPosition().isEmpty() && employeeRequestDao.getEmpInfo().isEmpty()){
                throw new MissingEmployeeDataException("Please pass the required fields to create a new Employee");
            }

            // Check if an Employee already exists with the passed in Email Address
            Optional<Employee> existEmployee = employeeRepository.findEmployeeByEmpEmail(employeeRequestDao.getEmpEmail());

            if (existEmployee.isPresent()) throw new EmployeeAlreadyExistsException("");

            // Create an Employee Object from the Request Dao and store it into the Database
            Employee employee = new Employee();
            employee.setEmpName(employeeRequestDao.getEmpName());
            employee.setEmpEmail(employeeRequestDao.getEmpEmail());
            employee.setEmpContactNum(employee.getEmpContactNum());
            employee.setEmpPosition(employeeRequestDao.getEmpPosition());
            employee.setEmpInfo(employeeRequestDao.getEmpInfo());

           // Generate the Current Date
            LocalDate today = LocalDate.now();
            employee.setEmpStartDate(today);

            // Save the Employee to the Database
            employeeRepository.save(employee);

        } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void updateEmployee(long employeeId, EmployeeRequestDao employeeRequestDao) {

        try {

            // Get the Employee from the Database based on the passed in id
            Optional<Employee> employee = employeeRepository.findById(employeeId);

            // Check if the Employee exists or not
            if (employee.isPresent()) {

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

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void deleteEmployee(long employeeId) {

        try {

            // Get the Employee based on the passed in id
            Employee employee = employeeRepository.findById(employeeId).orElseThrow();

            // Check if the Employee exists with the given id and delete the Employee
           employeeRepository.delete(employee);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
