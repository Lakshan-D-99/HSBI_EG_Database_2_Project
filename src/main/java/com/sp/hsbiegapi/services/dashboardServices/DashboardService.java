package com.sp.hsbiegapi.services.dashboardServices;

import com.sp.hsbiegapi.daos.ResponseDaos.dashboardResponseDaos.DashboardEnergyProductionResponse;
import com.sp.hsbiegapi.daos.ResponseDaos.dashboardResponseDaos.DashboardResponseDao;

import java.util.List;

public interface DashboardService {

    // Get Basic Dashboard data
    DashboardResponseDao getDashboardData();

    // Get Dashboard Data to display on the Energy chart
    List<DashboardEnergyProductionResponse> getAllEnergyProductionDashboard();
}
