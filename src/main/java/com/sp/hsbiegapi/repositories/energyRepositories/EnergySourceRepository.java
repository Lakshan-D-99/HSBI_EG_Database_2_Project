package com.sp.hsbiegapi.repositories.energyRepositories;

import com.sp.hsbiegapi.daos.ResponseDaos.joinResponses.LocationEnergySourceResponse;
import com.sp.hsbiegapi.models.energyModels.EnergySource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnergySourceRepository extends JpaRepository<EnergySource,Long> {

    // Extern Query to get All the Energy Sources with their Locations
    @Query(value = """
            SELECT energysource.id, location.loc_name, energysource.energy_type,energysource.energy_capacity, location.loc_address FROM energysource 
            JOIN energysource_location ON energysource.id = energysource_location.energysource_id
            JOIN location ON location.id = energysource_location.location_id""",nativeQuery = true)
    List<LocationEnergySourceResponse> findAllEnergySourcesWithLocations();
}
