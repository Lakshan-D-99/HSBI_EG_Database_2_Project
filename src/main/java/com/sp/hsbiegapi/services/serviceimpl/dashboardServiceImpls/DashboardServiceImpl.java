package com.sp.hsbiegapi.services.serviceimpl.dashboardServiceImpls;

import com.sp.hsbiegapi.daos.ResponseDaos.dashboardResponseDaos.DashboardEnergyProductionResponse;
import com.sp.hsbiegapi.daos.ResponseDaos.dashboardResponseDaos.DashboardResponseDao;
import com.sp.hsbiegapi.services.dashboardServices.DashboardService;
import com.sp.hsbiegapi.services.energyServices.AlertSignalService;
import com.sp.hsbiegapi.services.energyServices.DailyProductionService;
import com.sp.hsbiegapi.services.energyServices.EnergySourceService;
import com.sp.hsbiegapi.services.memServices.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final MemberService memberService;
    private final EnergySourceService energySourceService;
    private final DailyProductionService dailyProductionService;
    private final AlertSignalService alertSignalService;

    @Autowired
    public DashboardServiceImpl(MemberService memberService, EnergySourceService energySourceService, DailyProductionService dailyProductionService, AlertSignalService alertSignalService) {
        this.memberService = memberService;
        this.energySourceService = energySourceService;
        this.dailyProductionService = dailyProductionService;
        this.alertSignalService = alertSignalService;
    }


    @Override
    @Transactional
    public DashboardResponseDao getDashboardData() {

        try {
            // Get all the Active through the Member Service
            String memAmount = memberService.getAllActiveMembers();

            // Get all the Energy Sources locations through the Energy Source Service
            String engLocAmount = energySourceService.getAmountOfEnergyLocations();

            // Get all the amount of Energy Produced through the Daily production service
            String engAmount = dailyProductionService.getAllTheEnergyCapacity();

            // Get all the Alerts through the Alerts Service
            String aleAmount = alertSignalService.getAlertAmount();

            return new DashboardResponseDao(memAmount,engLocAmount,engAmount,aleAmount);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<DashboardEnergyProductionResponse> getAllEnergyProductionDashboard() {

        try {
            return dailyProductionService.getAllEnergyProduction();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
