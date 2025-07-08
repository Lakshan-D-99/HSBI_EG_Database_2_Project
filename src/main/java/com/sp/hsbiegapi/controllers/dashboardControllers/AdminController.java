package com.sp.hsbiegapi.controllers.dashboardControllers;

import com.sp.hsbiegapi.daos.ResponseDaos.dashboardResponseDaos.DashboardEnergyProductionResponse;
import com.sp.hsbiegapi.daos.ResponseDaos.dashboardResponseDaos.DashboardResponseDao;
import com.sp.hsbiegapi.services.dashboardServices.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api_v1/admin")
public class AdminController {

    private final DashboardService dashboardService;

    public AdminController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    // Get all the Dashboard Data
    @GetMapping("/dashboard-data")
    public DashboardResponseDao getDashboardData(){
        return dashboardService.getDashboardData();
    }

    // Get all the Energy Data to display in the Dashboard Chart
    @GetMapping("/energy-data")
    public List<DashboardEnergyProductionResponse> getAllDashboardEnergy(){
        return dashboardService.getAllEnergyProductionDashboard();
    }
}
