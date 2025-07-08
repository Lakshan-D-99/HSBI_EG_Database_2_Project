package com.sp.hsbiegapi.daos.ResponseDaos.dashboardResponseDaos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DashboardResponseDao {

    private String amountActiveMembers;
    private String amountOfLocations;
    private String amountOfAllEnergyProduction;
    private String amountOfAlerts;


}
