package com.sp.hsbiegapi.services.serviceimpl.joinTableServiceImpl;

import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.EnergySourceResponseDao;
import com.sp.hsbiegapi.models.energyModels.EnergySource;
import com.sp.hsbiegapi.models.locModels.Location;
import com.sp.hsbiegapi.repositories.energyRepositories.EnergySourceRepository;
import com.sp.hsbiegapi.repositories.locatiRepositories.LocationRepository;
import com.sp.hsbiegapi.services.joinTableServices.EnergySourceLocationService;
import com.sp.hsbiegapi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class EnergySourceLocationServiceImpl implements EnergySourceLocationService {

    private final EnergySourceRepository energySourceRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public EnergySourceLocationServiceImpl(EnergySourceRepository energySourceRepository, LocationRepository locationRepository) {
        this.energySourceRepository = energySourceRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    @Transactional
    public void addLocationToEnergySource(long energySourceId, long locationId) {

        // Get the Energy Source from the Database
        EnergySource energySource = energySourceRepository.findById(energySourceId).orElseThrow();

        // Get the Location from the Database
        Location location = locationRepository.findById(locationId).orElseThrow();

        // Add Location to the Energy Source
        energySource.addLocation(location);

        energySourceRepository.save(energySource);
    }

    @Override
    @Transactional
    public void removeLocationFromEnergySource(long energySourceId, long locationId) {

        // Get the Energy Source from the Database
        EnergySource energySource = energySourceRepository.findById(energySourceId).orElseThrow();

        // Get the Location from the Database
        Location location = locationRepository.findById(locationId).orElseThrow();

        // Remove the Location from the Energy Source
        energySource.removeLocation(location);

        energySourceRepository.save(energySource);
    }

    @Override
    public Set<Location> getAllLocationsOfEnergySource(long energySourceId) {

        // Get the Energy Location from the Database
        EnergySource energySource = energySourceRepository.findById(energySourceId).orElseThrow();

        return energySource.getLocationSet();
    }

    @Override
    public Set<EnergySourceResponseDao> getAllEnergySourcesOfLocation(long locationId) {

        // Get the Location from the Database
        Location location = locationRepository.findById(locationId).orElseThrow();

        Set<EnergySource> energySourceSet = location.getEnergySourceSet();

        Set<EnergySourceResponseDao> energySourceResponseDaoSet = new HashSet<>();

        energySourceSet.forEach(energySource -> energySourceResponseDaoSet.add(Mapper.conEntityToDao(energySource)));

        return energySourceResponseDaoSet;
    }
}
