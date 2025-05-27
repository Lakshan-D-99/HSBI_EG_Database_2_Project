package com.sp.hsbiegapi.services.energyServices;

import com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao.EnergySourceRequestsDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.EnergySourceResponseDao;
import com.sp.hsbiegapi.models.energyModels.EnergySource;

import java.util.List;

public interface EnergySourceService {

    // Get all the Energy Sources from the Database
    List<EnergySourceResponseDao> getAllEnergySources();

    // Get a single Energy Source based on the id
    EnergySourceResponseDao getEnergySource(long energySourceId);

    // Add a new Energy Source into the Database
    void addNewEnergySource(EnergySourceRequestsDao energySourceRequestsDao);

    // Update an existing Energy Source in the Database
    void updateEnergySource(long energySourceId, EnergySourceRequestsDao energySourceRequestsDao);

    // Delete an existing Energy Source from the Database
    void deleteEnergySource(long energySourceId);
}
