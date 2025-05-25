package com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MaintenanceRequestsDao {

    private String maintenanceDetails;
    private double maintenanceCost;
    private String maintenanceStatus;
    private LocalDate maintenanceDate;
    private long energySourceId;
}
