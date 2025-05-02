package com.sp.hsbiegapi.models.emploModels;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String empName;
    @Column(unique = true)
    private String empEmail;
    private String empContactNum;
    private String empPosition;
    private LocalDate empStartDate;
    private String empInfo;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Payment> paymentList = new ArrayList<>();

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Qualification> qualificationList = new ArrayList<>();

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<TimeRecord> timeRecordList = new ArrayList<>();

    public void addPayment(Payment payment){
        paymentList.add(payment);
    }

    public void removePayment(Payment payment){
        paymentList.remove(payment);
    }

    public void addQualification(Qualification qualification){
        qualificationList.add(qualification);
    }

    public void removeQualification(Qualification qualification){
        qualificationList.remove(qualification);
    }

    public void addTimeKeeping(TimeRecord timeRecord){
        timeRecordList.add(timeRecord);
    }

    public void removeTimeKeeping(TimeRecord timeRecord){
        timeRecordList.remove(timeRecord);
    }

}
