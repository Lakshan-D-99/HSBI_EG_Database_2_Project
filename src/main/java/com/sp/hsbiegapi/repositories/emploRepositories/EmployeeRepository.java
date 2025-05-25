package com.sp.hsbiegapi.repositories.emploRepositories;

import com.sp.hsbiegapi.models.emploModels.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
