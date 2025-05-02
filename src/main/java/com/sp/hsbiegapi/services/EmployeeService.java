package com.sp.hsbiegapi.services;

import com.sp.hsbiegapi.daos.RequestDaos.EmployeeRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.EmployeeResponseDao;

import java.util.List;

public interface EmployeeService {

    // Get all the Employees from the Database
    List<EmployeeResponseDao> getAllEmployees();

    // Get a Single Employee based on the pass employee id
    EmployeeResponseDao getEmployee(long employeeId);

    // Create and Store an Employee into the Database
    void addNewEmployee(EmployeeRequestDao employeeRequestDao);

    // Update an existing Employee
    void updateEmployee(long employeeId, EmployeeRequestDao employeeRequestDao);

    // Delete an existing Employee from the Database
    void deleteEmployee(long employeeId);
}
