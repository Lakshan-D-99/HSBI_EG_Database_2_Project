package com.sp.hsbiegapi.services.energyServices;

import com.sp.hsbiegapi.models.energyModels.EnergySource;

import java.util.List;

public interface EnergySourceService {

    // Get all the Energy Sources from the Database
    List<EnergySource> getAllEnergySources();

    // Get a single Energy Source based on the id
    EnergySource getEnergySource(long energySourceId);

    // Add a new Energy Source into the Database
    void addNewEnergySource(EnergySource energySource);

    // Update an existing Energy Source in the Database
    void updateEnergySource(long energySourceId, EnergySource energySource);

    // Delete an existing Energy Source from the Database
    void deleteEnergySource(long energySourceId);
}
