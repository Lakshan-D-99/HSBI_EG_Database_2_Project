package com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos.TimeRecordRequests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TimeRecordRequestDao {

    private double workHours;
    private LocalDate workDay;
    private String job;
    private long empId;
}
