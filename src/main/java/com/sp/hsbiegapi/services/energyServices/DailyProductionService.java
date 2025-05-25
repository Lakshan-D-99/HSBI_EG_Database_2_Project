package com.sp.hsbiegapi.services.energyServices;

import com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao.DailyProductionRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.DailyProductionResponseDao;

import java.util.List;

public interface DailyProductionService {

    // Get all the Production Details of a specific Energy Source
    List<DailyProductionResponseDao> getAllProductionOfEnergySource(long energySourceId);

    // Get a single Daily Production Details
    DailyProductionResponseDao getSingleDailyProd(long dailyProdId);

    // Add the Production Details to a specific Energy Source
    void addDailyProductionToEnergySource(long energySourceId, DailyProductionRequestDao dailyProductionRequestDao);

    // Remove the Production Details of a specific Energy Source
    void removeDailyProductionOfEnergySource(long dailyProductionId);


}
