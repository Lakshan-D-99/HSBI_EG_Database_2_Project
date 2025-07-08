package com.sp.hsbiegapi.services.energyServices;

import com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao.EnergySourceRequestsDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.EnergySourceResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.joinResponses.LocationEnergySourceResponse;
import com.sp.hsbiegapi.models.energyModels.EnergySource;

import java.util.List;

public interface EnergySourceService {

    // Get all the Energy Sources from the Database
    List<EnergySourceResponseDao> getAllEnergySources();

    // Get a single Energy Source based on the id
    EnergySourceResponseDao getEnergySource(long energySourceId);

    // Get all the running Energy Sources with their locations
    List<LocationEnergySourceResponse> getAllEnergySourcesAndLocations();

    // Get the amount of all the Locations where an Energy Source is running
    String getAmountOfEnergyLocations();

    // Add a new Energy Source into the Database
    void addNewEnergySource(EnergySourceRequestsDao energySourceRequestsDao);

    // Delete an existing Energy Source from the Database
    void deleteEnergySource(long energySourceId);
}
