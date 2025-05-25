package com.sp.hsbiegapi.services.emploServices;

import com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos.TimeRecordRequests.TimeRecordRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.TimeRecordResponses.TimeRecordResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.TimeRecordResponses.WorkingHoursResponseDao;

import java.time.LocalDate;
import java.util.List;

public interface TimeRecordService {

    // Get all the TimeKeeping Records of a specific Employee
    List<TimeRecordResponseDao> getAllEmployeeTimeRecords(long employeeId);

    // Get a Single TimeKeeping Record of a specific Employee
    TimeRecordResponseDao getSingleEmployeeTimeRecord(long recordId);

    // Add a new TimeKeeping Record to an Employee
    void addNewTimeRecordToEmployee(long employeeId, TimeRecordRequestDao timeRecordRequestDao);

    // Update the TimeKeeping Record of an Employee
    void updateTimeRecordOfEmployee(long employeeId,long recordId, TimeRecordRequestDao timeRecordRequestDao);

    // Delete the TimeKeeping Record of an Employee
    void deleteTimeRecordOfEmployee(long recordId);

    // <!-- Internal Methods -->

    // Get working hours between two Dates -> essential to generate a Payment
    WorkingHoursResponseDao getEmployeeHoursBetweenTwoDates(long employeeId, LocalDate startDate, LocalDate endDate);
}
