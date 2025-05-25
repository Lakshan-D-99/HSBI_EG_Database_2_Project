package com.sp.hsbiegapi.services.serviceimpl.energyServiceImpls;

import com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao.DailyProductionRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.DailyProductionResponseDao;
import com.sp.hsbiegapi.repositories.energyRepositories.DailyProductionRepository;
import com.sp.hsbiegapi.repositories.energyRepositories.EnergySourceRepository;
import com.sp.hsbiegapi.services.energyServices.DailyProductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DailyProductionServiceImpl implements DailyProductionService {

    private final DailyProductionRepository dailyProductionRepository;
    private final EnergySourceRepository energySourceRepository;

    @Autowired
    public DailyProductionServiceImpl(DailyProductionRepository dailyProductionRepository, EnergySourceRepository energySourceRepository) {
        this.dailyProductionRepository = dailyProductionRepository;
        this.energySourceRepository = energySourceRepository;
    }

    @Override
    public List<DailyProductionResponseDao> getAllProductionOfEnergySource(long energySourceId) {
        return List.of();
    }

    @Override
    public DailyProductionResponseDao getSingleDailyProd(long dailyProdId) {
        return null;
    }

    @Override
    public void addDailyProductionToEnergySource(long energySourceId, DailyProductionRequestDao dailyProductionRequestDao) {

    }

    @Override
    public void removeDailyProductionOfEnergySource(long dailyProductionId) {

    }
}
