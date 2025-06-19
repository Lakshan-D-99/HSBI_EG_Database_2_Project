package com.sp.hsbiegapi.services.serviceimpl.locatiServiceImpls;

import com.sp.hsbiegapi.daos.RequestDaos.locatiRequestDaos.LocationRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.locatiResponseDaos.LocationResponseDao;
import com.sp.hsbiegapi.models.locModels.Location;
import com.sp.hsbiegapi.repositories.locatiRepositories.LocationRepository;
import com.sp.hsbiegapi.services.locatiServices.LocationService;
import com.sp.hsbiegapi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<LocationResponseDao> getAllLocations() {

        // Get all the Locations from the Database
        List<Location> locationList = locationRepository.findAll();

        // Create a LocationResponseDao list to store converted Locations
        List<LocationResponseDao> locationResponseDaoList = new ArrayList<>();

        // Convert Locations into LocationsResponseDaos
        locationList.forEach(location -> locationResponseDaoList.add(Mapper.conEntityToDao(location)));

        // Return the DAOs list
        return locationResponseDaoList;
    }

    @Override
    public LocationResponseDao getLocation(long locationId) {

        // Get the Location based on the passed in Location id
        Location location = locationRepository.findById(locationId).orElseThrow();

        // Convert into a DAO and return it
        return Mapper.conEntityToDao(location);
    }

    @Override
    public int getLocationCapacity(long locationId) {

        // Get the Location based on the passed in Location id
        Location location = locationRepository.findById(locationId).orElseThrow();

        // Get the Location Capacity from the Location Object and return it
        return location.getLocCapacity();
    }

    @Override
    public void saveLocation(LocationRequestDao locationRequestDao) {

        // Check if the Required fields are passed in
        if (locationRequestDao.getLocName().isEmpty() && locationRequestDao.getLocAddress().isEmpty() && locationRequestDao.getLocGeoDetails().isEmpty() && locationRequestDao.getLocStatus().isEmpty() && locationRequestDao.getLocCapacity() == 0 && locationRequestDao.getStartDate() == null){
            System.out.println("Please provide all the necessray Details to create and save a new Location");
        }

        // Create a Location Object based on the passed in Location Details
        Location location = Mapper.conDaoToEntity(locationRequestDao);

        // Save the Location to the Database
        locationRepository.save(location);

    }

    @Override
    //TODO: To be Implemented
    public void updateLocation(long locationId, LocationRequestDao locationRequestDao) {

    }

    @Override
    public void deleteLocation(long locationId) {

        // Get the Location based on passed in Location id
        Location location = locationRepository.findById(locationId).orElseThrow();

        // Remove the Location from the Database
        locationRepository.delete(location);
    }
}
