package com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos;

import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.TimeRecordResponses.TimeRecordResponseDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeResponseDao {

    private long empId;
    private String empName;
    private String empEmail;
    private String empContactNumber;
    private String empPosition;
    private LocalDate empStartDate;
    private List<PaymentResponseDao> paymentResponseDaoList = new ArrayList<>();
    private List<QualificationResponseDao> qualificationResponseDaoList = new ArrayList<>();
    private List<TimeRecordResponseDao> timeRecordResponseDaoList = new ArrayList<>();


}
