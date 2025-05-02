package com.sp.hsbiegapi.daos.RequestDaos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnergySourceRequestsDao {

    private double energyCapacity;
    private double energyType;
    private double energyAvailable;
    private String startDate;

}
