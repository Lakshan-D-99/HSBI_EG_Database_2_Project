package com.sp.hsbiegapi.daos.ResponseDaos.memberResponseDaos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberResponseDao {

    private long id;
    private String memberName;
    private String memMatNumber;
    private String memEmail;
    private double memberContribution;
    private String memberStats;
    private LocalDate memberJoinDate;
    private String memberPaymentType;
}
