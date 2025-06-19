package com.sp.hsbiegapi.services.joinTableServices;


import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.EnergySourceResponseDao;
import com.sp.hsbiegapi.models.locModels.Location;

import java.util.Set;

public interface EnergySourceLocationService {

    // Add a Location to an Energy Source
    void addLocationToEnergySource(long energySourceId, long locationId);

    // Remove a Location from an Energy Source
    void removeLocationFromEnergySource(long energySourceId, long locationId);

    // Get all the Locations of a specific Energy Source
    Set<Location> getAllLocationsOfEnergySource(long energySourceId);

    // Get all the Energy Sources of a specific Location
    Set<EnergySourceResponseDao> getAllEnergySourcesOfLocation(long locationId);
}
