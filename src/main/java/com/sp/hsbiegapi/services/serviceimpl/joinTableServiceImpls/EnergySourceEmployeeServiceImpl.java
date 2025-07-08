package com.sp.hsbiegapi.services.serviceimpl.joinTableServiceImpls;

import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.EmployeeResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.EnergySourceResponseDao;
import com.sp.hsbiegapi.models.emploModels.Employee;
import com.sp.hsbiegapi.models.energyModels.EnergySource;
import com.sp.hsbiegapi.repositories.emploRepositories.EmployeeRepository;
import com.sp.hsbiegapi.repositories.energyRepositories.EnergySourceRepository;
import com.sp.hsbiegapi.services.joinTableServices.EnergySourceEmployeeService;
import com.sp.hsbiegapi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class EnergySourceEmployeeServiceImpl implements EnergySourceEmployeeService {

    private final EnergySourceRepository energySourceRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EnergySourceEmployeeServiceImpl(EnergySourceRepository energySourceRepository, EmployeeRepository employeeRepository) {
        this.energySourceRepository = energySourceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public void assignEmployeeToEnergySource(long energyId, long empId) {

        // Get the Energy Source based on the passed in id
        EnergySource energySource = energySourceRepository.findById(energyId).orElseThrow();

        // Get the Employee based on the passed in Employee id
        Employee employee = employeeRepository.findById(empId).orElseThrow();

        // Assign Employee to the Project
        energySource.addEmployee(employee);

        // Save the Energy Source
        energySourceRepository.save(energySource);
    }

    @Override
    @Transactional
    public void removeEmployeeFromEnergySource(long energyId, long empId) {

        // Get the Energy Source based on the passed in id
        EnergySource energySource = energySourceRepository.findById(energyId).orElseThrow();

        // Get the Employee based on the passed in id
        Employee employee = employeeRepository.findById(empId).orElseThrow();

        // Remove the Employee from the Energy Source
        energySource.removeEmployee(employee);

        // Save the Energy Source
        energySourceRepository.save(energySource);

    }

    @Override
    @Transactional
    public Set<EmployeeResponseDao> getAllEmployeesOfEnergySource(long energyId) {

        // Check if the Energy Source exists based on the passed in Energy Source id
        EnergySource energySource = energySourceRepository.findById(energyId).orElseThrow();

        // Get all the Employees of that specific Energy Source
        Set<Employee> employeeSet =  energySource.getEmployeeSet();

        Set<EmployeeResponseDao> employeeResponseDaoSet = new HashSet<>();

        employeeSet.forEach(employee -> employeeResponseDaoSet.add(Mapper.conEntityToDao(employee)));

        return employeeResponseDaoSet;
    }

    @Override
    @Transactional
    public Set<EnergySourceResponseDao> getAllEnergySourcesOfEmployee(long empId) {

        // Check if the Employee based on the passed in id exists in the DB
        Employee employee = employeeRepository.findById(empId).orElseThrow();

        // Get all the Energy Sources of the passed in Employee
        Set<EnergySource> energySourceSet = employee.getEnergySourceSet();

        Set<EnergySourceResponseDao> energySourceResponseDaoSet = new HashSet<>();

        // Convert all the Energy Sources into DAOs and return them
        energySourceSet.forEach(energySource -> energySourceResponseDaoSet.add(Mapper.conEntityToDao(energySource)));

        return energySourceResponseDaoSet;
    }
}
