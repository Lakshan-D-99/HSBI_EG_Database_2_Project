package com.sp.hsbiegapi.services.serviceimpl.locatiServiceImpls;

import com.sp.hsbiegapi.apiServices.koordinateApi.GeoCoordinateApiService;
import com.sp.hsbiegapi.apiServices.koordinateApi.GeoCoordinateRequest;
import com.sp.hsbiegapi.apiServices.koordinateApi.GeoCoordinateResponse;
import com.sp.hsbiegapi.daos.RequestDaos.locatiRequestDaos.LocationRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.locatiResponseDaos.LocationResponseDao;
import com.sp.hsbiegapi.models.locModels.Location;
import com.sp.hsbiegapi.repositories.locatiRepositories.LocationRepository;
import com.sp.hsbiegapi.services.locatiServices.LocationService;
import com.sp.hsbiegapi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final GeoCoordinateApiService geoCoordinateApiService;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository, GeoCoordinateApiService geoCoordinateApiService) {
        this.locationRepository = locationRepository;
        this.geoCoordinateApiService = geoCoordinateApiService;
    }

    @Override
    public List<LocationResponseDao> getAllLocations() {

        try {
            // Get all the Locations from the Database
            List<Location> locationList = locationRepository.findAll();

            // Create a LocationResponseDao list to store converted Locations
            List<LocationResponseDao> locationResponseDaoList = new ArrayList<>();

            // Convert Locations into LocationsResponseDaos
            locationList.forEach(location -> locationResponseDaoList.add(Mapper.conEntityToDao(location)));

            // Return the DAOs list
            return locationResponseDaoList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public LocationResponseDao getLocation(long locationId) {

        try {
            // Get the Location based on the passed in Location id
            Location location = locationRepository.findById(locationId).orElseThrow();

            // Convert into a DAO and return it
            return Mapper.conEntityToDao(location);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getLocationCapacity(long locationId) {

        try {
            // Get the Location based on the passed in Location id
            Location location = locationRepository.findById(locationId).orElseThrow();

            // Get the Location Capacity from the Location Object and return it
            return location.getLocCapacity();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void saveLocation(LocationRequestDao locationRequestDao) {

        try {
            // Check if the Required fields are passed in
            if (locationRequestDao.getLocName().isEmpty() && locationRequestDao.getLocAddress().isEmpty() && locationRequestDao.getLocStatus().isEmpty() && locationRequestDao.getLocCapacity() == 0 && locationRequestDao.getStartDate() == null){
                System.out.println("Please provide all the necessray Details to create and save a new Location");
            }

            // Generate Geocoordinate Response using the GeoService Api
            GeoCoordinateResponse gcResponse = geoCoordinateApiService.getData(locationRequestDao.getLocAddress());

            // Create a Location Object based on the passed in Location Details
            Location location = Mapper.conDaoToEntity(locationRequestDao);
            location.setLocLatitude(gcResponse.latitude());
            location.setLocLongitude(gcResponse.longitude());

            // Save the Location to the Database
            locationRepository.save(location);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
