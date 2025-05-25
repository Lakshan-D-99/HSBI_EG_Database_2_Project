package com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MaintenanceResponseDao {

    private long id;
    private String maintenanceDetails;
    private double maintenanceCost;
    private String maintenanceStatus;
    private LocalDate maintenanceDate;
    private long energySourceId;
}
