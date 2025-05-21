package com.sp.hsbiegapi.daos.ResponseDaos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnergySourceResponseDao {

    private long energySourceId;
    private double energyCapacity;
    private double energyType;
    private double energyAvailable;
    private LocalDate startDate;
}
