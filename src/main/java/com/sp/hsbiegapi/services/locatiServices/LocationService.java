package com.sp.hsbiegapi.services.locatiServices;

import com.sp.hsbiegapi.apiServices.koordinateApi.GeoCoordinateRequest;
import com.sp.hsbiegapi.daos.RequestDaos.locatiRequestDaos.LocationRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.locatiResponseDaos.LocationResponseDao;

import java.util.List;

public interface LocationService {

    // Get all the Available Locations in the Database
    List<LocationResponseDao> getAllLocations();

    // Get a Single Location based on the passed in Location id
    LocationResponseDao getLocation(long locationId);

    // Get the current Capacity of a Location
    int getLocationCapacity(long locationId);

    // Create a new Location and Add it into the Database
    void saveLocation(LocationRequestDao locationRequestDao);

}
