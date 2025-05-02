package com.sp.hsbiegapi.controllers;

import com.sp.hsbiegapi.daos.RequestDaos.EmployeeRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.EmployeeResponseDao;
import com.sp.hsbiegapi.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Get all the Employees from the Database
    @GetMapping("/all")
    public List<EmployeeResponseDao> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    // Get a Single Employee from the Database based on the passed in id
    @GetMapping("/{employeeId}")
    public EmployeeResponseDao getSingleEmployee(@PathVariable long employeeId){
        return employeeService.getEmployee(employeeId);
    }

    // Create a new Employee and save it into the Database
    @PostMapping("/new")
    public String createEmployee(@RequestBody EmployeeRequestDao employeeRequestDao){
        employeeService.addNewEmployee(employeeRequestDao);
        return "A new Employee has been created and saved to the Database";
    }

    // Update an existing Employee from the Database
    @PutMapping("/{employeeId}")
    public String updateEmployee(@PathVariable long employeeId,@RequestBody EmployeeRequestDao employeeRequestDao){
        employeeService.updateEmployee(employeeId,employeeRequestDao);
        return "Employee has been updated";
    }

    // Delete an existing Employee from the Database
    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable long employeeId){
        employeeService.deleteEmployee(employeeId);
        return "Employee with the ID: " + employeeId + " has been deleted";
    }

}
