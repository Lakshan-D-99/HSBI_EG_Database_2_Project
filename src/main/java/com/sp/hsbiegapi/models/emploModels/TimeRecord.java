package com.sp.hsbiegapi.models.emploModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "time_keeping")
public class TimeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double workHours;
    private LocalDate workDay;
    private String job;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;
}
