package com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeRequestDao {

    private String empName;
    private String empEmail;
    private String empContactNumber;
    private String empPosition;
    private LocalDate empStartDate;
    private String empInfo;

}
