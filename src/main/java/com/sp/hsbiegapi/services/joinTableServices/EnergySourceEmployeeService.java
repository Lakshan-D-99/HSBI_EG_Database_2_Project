package com.sp.hsbiegapi.services.joinTableServices;

import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.EmployeeResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.EnergySourceResponseDao;

import java.util.Set;

public interface EnergySourceEmployeeService {

    // Assign an Employee into an Energy Source
    void assignEmployeeToEnergySource(long energyId,long empId);

    // Remove an Employee from an Energy Source
    void removeEmployeeFromEnergySource(long energyId, long empId);

    // Get all the Employees of an Energy Source
    Set<EmployeeResponseDao> getAllEmployeesOfEnergySource(long energyId);

    // Get all the Energy Source of an Employee
    Set<EnergySourceResponseDao> getAllEnergySourcesOfEmployee(long empId);
}
