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
@Table(name = "energysource")
public class EnergySource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long energySourceId;
    private double energyCapacity;
    private double energyType;
    private double energyAvailable;
    private LocalDate startDate;
}
