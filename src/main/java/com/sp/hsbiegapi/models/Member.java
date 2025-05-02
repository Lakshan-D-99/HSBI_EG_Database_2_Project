package com.sp.hsbiegapi.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String memberName;
    @Column(unique = true)
    private String memberMatNumber;
    @Column(unique = true)
    private String memberEmail;
    private double memberContribution;
    private String memberStatus;
    private LocalDate memberJoinDate;
    private String memberPaymentType;
}
