package com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.TimeRecordResponses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WorkingHoursResponseDao {

    private long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double workHours;
}
