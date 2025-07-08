package com.sp.hsbiegapi.services.emploServices;

import com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos.QualificationRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.QualificationResponseDao;

import java.util.List;

public interface QualificationService {

    // Get all the Qualifications of an Employee
    List<QualificationResponseDao> getAllEmployeeQualifications(long employeeId);

    // Get a single Qualification Object
    QualificationResponseDao getSingleQualification(long qualificationId);

    // Add a new Qualification to an existing Employee
    void addQualificationToEmployee(long employeeId, QualificationRequestDao qualificationRequestDao);

    // Delete an existing Qualification of an Employee
    void deleteEmployeeQualification(long qualificationId);
}
