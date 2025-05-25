package com.sp.hsbiegapi.repositories.emploRepositories;

import com.sp.hsbiegapi.models.emploModels.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification,Long> {

    @Query("SELECT q FROM Qualification q WHERE q.employee.id= :empId")
    List<Qualification> findAllByEmployeeId(Long empId);


}
