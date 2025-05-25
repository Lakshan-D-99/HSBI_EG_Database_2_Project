package com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentResponseDao {

    private long id;
    private double paymentAmount;
    private String paymentType;
    private String paymentInvoiceNum;
    private LocalDate paymentDate;
    private long empId;
}
