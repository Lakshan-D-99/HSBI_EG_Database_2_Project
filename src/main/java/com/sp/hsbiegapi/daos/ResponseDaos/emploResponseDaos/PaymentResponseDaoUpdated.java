package com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Setter
public class PaymentResponseDaoUpdated {

    private long id;
    private double paymentAmount;
    private LocalDate paymentDate;
    private String empName;

    public PaymentResponseDaoUpdated(long id, double paymentAmount, Date paymentDate, String empName) {
        this.id = id;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate.toLocalDate();
        this.empName = empName;
    }
}
