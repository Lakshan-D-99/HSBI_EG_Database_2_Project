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
public class TimeRecordResponseDao {

    private long id;
    private double workHours;
    private LocalDate workDay;
    private String job;
    private long empId;
}
