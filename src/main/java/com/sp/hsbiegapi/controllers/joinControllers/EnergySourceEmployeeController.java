package com.sp.hsbiegapi.controllers.joinControllers;

import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.EmployeeResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.EnergySourceResponseDao;
import com.sp.hsbiegapi.services.joinTableServices.EnergySourceEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api_v1/energy-sources")
public class EnergySourceEmployeeController {

    private final EnergySourceEmployeeService energySourceEmployeeService;

    @Autowired
    public EnergySourceEmployeeController(EnergySourceEmployeeService energySourceEmployeeService) {
        this.energySourceEmployeeService = energySourceEmployeeService;
    }

    // Assign an Employee into an Energy Source
    @PutMapping("/engId={energySourceId}/employees/add/empId={employeeId}")
    public String addEmployeeToEnergySource(@PathVariable long energySourceId, @PathVariable long employeeId){
        energySourceEmployeeService.assignEmployeeToEnergySource(energySourceId, employeeId);
        return "Employee has been assigned successfully";
    }

    // Remove an Employee from an Energy Source
    @PutMapping("/engId={energySourceId}/employees/remove/empId={employeeId}")
    public String remEmployeeFromEnergySource(@PathVariable long energySourceId, @PathVariable long employeeId){
        energySourceEmployeeService.removeEmployeeFromEnergySource(energySourceId, employeeId);
        return "Employee has been removed from the Energy Source";
    }

    // Get all the Employees of a specific Energy Source
    @GetMapping("/engId={energySourceId}/employees/all-employees")
    public Set<EmployeeResponseDao> getAllEmployeeOfEnergySource(@PathVariable long energySourceId){
        return energySourceEmployeeService.getAllEmployeesOfEnergySource(energySourceId);
    }

    // Get all the Energy Source of a specific Employee
    @GetMapping("/employees/all/empId={employeeId}/energySources")
    public Set<EnergySourceResponseDao> getAllEnergySourceOfEmployee(@PathVariable long employeeId){
        return energySourceEmployeeService.getAllEnergySourcesOfEmployee(employeeId);
    }
}
