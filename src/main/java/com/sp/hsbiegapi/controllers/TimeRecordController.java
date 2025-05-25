package com.sp.hsbiegapi.controllers;

import com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos.TimeRecordRequests.TimeRecordRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.TimeRecordResponses.TimeRecordResponseDao;
import com.sp.hsbiegapi.services.emploServices.TimeRecordService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/timeRecords")
public class TimeRecordController {

    private final TimeRecordService timeRecordService;

    @Autowired
    public TimeRecordController(TimeRecordService timeRecordService) {
        this.timeRecordService = timeRecordService;
    }

    // Get all the Time Records of a specific Employee
    @GetMapping("/employees/empId={employeeId}")
    public List<TimeRecordResponseDao> getAllTimeRecordOfEmployee(@PathVariable long employeeId){
        return timeRecordService.getAllEmployeeTimeRecords(employeeId);
    }

    // Get a single Time Record of an Employee
    @GetMapping("/timeId={timeRecordId}")
    public TimeRecordResponseDao getSingleTimeRecord(@PathVariable long timeRecordId){
        return timeRecordService.getSingleEmployeeTimeRecord(timeRecordId);
    }

    // Add a new Time Record to an Employee
    @PostMapping("/empId={employeeId}/new")
    public String addEmployeeTimeRecord(@PathVariable long employeeId, @RequestBody TimeRecordRequestDao timeRecordRequestDao){
        timeRecordService.addNewTimeRecordToEmployee(employeeId, timeRecordRequestDao);
        return "A new Time Record has been added to the Employee";
    }

    // Update an existing Time Record of an Employee

    // Delete an existing Time Record of an Employee
    @DeleteMapping("/timeId={timeRecordId}")
    public String deleteTimeRecord(@PathVariable long timeRecordId){
        timeRecordService.deleteTimeRecordOfEmployee(timeRecordId);
        return "The Time record has been deleted";
    }
}
