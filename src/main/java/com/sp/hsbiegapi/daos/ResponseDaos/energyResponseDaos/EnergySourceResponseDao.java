package com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    private List<AlertSignalResponseDao> alertSignalResponseDaoList = new ArrayList<>();
    private List<DailyProductionResponseDao> dailyProductionResponseDaoList = new ArrayList<>();
    private List<MaintenanceResponseDao> maintenanceResponseDaoList = new ArrayList<>();
    private List<OperationalStatusResponseDao> operationalStatusResponseDaoList = new ArrayList<>();

}
