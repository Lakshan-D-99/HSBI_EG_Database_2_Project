package com.sp.hsbiegapi.controllers;

import com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos.QualificationRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.QualificationResponseDao;
import com.sp.hsbiegapi.services.emploServices.QualificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/qualifications")
public class QualificationController {

    private final QualificationService qualificationService;

    @Autowired
    public QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    // Get all the Qualifications of an Employee
    @GetMapping("/employeeId={empId}/qualifications/all")
    public List<QualificationResponseDao> getAllEmployeeQualification(@PathVariable long empId){
        return qualificationService.getAllEmployeeQualifications(empId);
    }

    // Get a Single Qualification details of an Employee
    @GetMapping("/qualificationId={quaId}")
    public QualificationResponseDao getSingleEmployeeQualification(@PathVariable long quaId){
        return qualificationService.getSingleQualification(quaId);
    }

    // Add a new Qualification to an Employee
    @PostMapping("/employeeId={empId}/new")
    public String addNewQualificationToEmployee(@PathVariable long empId, @RequestBody QualificationRequestDao qualificationRequestDao){
        qualificationService.addQualificationToEmployee(empId, qualificationRequestDao);
        return "A new Qualification Record has been added to the Employee with Id: " + empId + ".";
    }

    // Delete an existing Qualification from an Employee
    @DeleteMapping("/qualificationId={quaId}")
    public String deleteEmployeeQualification(@PathVariable long quaId){
        qualificationService.deleteEmployeeQualification(quaId);
        return "Qualification has been removed from the Employee";
    }

}
