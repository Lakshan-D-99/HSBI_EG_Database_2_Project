package com.sp.hsbiegapi.repositories.emploRepositories;

import com.sp.hsbiegapi.models.emploModels.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<Employee> getEmployeeByEmpEmail(String empEmail);
}
