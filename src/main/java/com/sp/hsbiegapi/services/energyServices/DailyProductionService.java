package com.sp.hsbiegapi.services.energyServices;

import com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao.DailyProductionRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.dashboardResponseDaos.DashboardEnergyProductionResponse;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.DailyProductionResponseDao;

import java.util.List;

public interface DailyProductionService {

    // Get all the Production Details of a specific Energy Source
    List<DailyProductionResponseDao> getAllProductionOfEnergySource(long energySourceId);

    // Get a single Daily Production Details
    DailyProductionResponseDao getSingleDailyProd(long dailyProdId);

    // Get all the sum of the Energy Capacity from the Database
    String getAllTheEnergyCapacity();

    // Get a List of all the Energy Production based on the Date
    List<DashboardEnergyProductionResponse> getAllEnergyProduction();

    public void addDailyProductionToEnergySource(long energySourceId, DailyProductionRequestDao dailyProductionRequestDao);

    public void removeDailyProductionOfEnergySource(long dailyProductionId);


}
