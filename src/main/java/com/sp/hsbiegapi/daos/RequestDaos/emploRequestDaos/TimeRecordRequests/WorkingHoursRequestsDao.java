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
public class WorkingHoursRequestsDao {

    private long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
}
