package com.sp.hsbiegapi.services.locatiServices;

import com.sp.hsbiegapi.daos.ResponseDaos.locatiResponseDaos.WeatherResponseDao;

import java.util.List;

public interface WeatherService {

    // Get all the Weather Records in the Database
    List<WeatherResponseDao> getAllWeatherData();

    // Get all the Weather Records of a specific Location based on passed in location id
    List<WeatherResponseDao> getAllWeatherDataOfLocation(long locationId);

    // Save Weather Data into the Database
    void addWeatherData(long locationId, String longitude, String latitude);


}
