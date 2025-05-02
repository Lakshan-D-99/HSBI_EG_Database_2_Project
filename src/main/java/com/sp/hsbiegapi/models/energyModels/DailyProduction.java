package com.sp.hsbiegapi.models.energyModels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dailyproduction")
public class DailyProduction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long dailyProdId;
    private LocalDate currentDate;
    private double dailyProdAmount;
}
