package com.sp.hsbiegapi.scheduledServices;

import com.sp.hsbiegapi.apiServices.weatherApi.WeatherApiService;
import com.sp.hsbiegapi.models.locModels.Location;
import com.sp.hsbiegapi.repositories.locatiRepositories.LocationRepository;
import com.sp.hsbiegapi.services.locatiServices.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduledTasks {

    private final LocationRepository locationRepository;
    private final WeatherService weatherService;

    @Autowired
    public ScheduledTasks(LocationRepository locationRepository, WeatherService weatherService, WeatherApiService weatherApiService) {
        this.locationRepository = locationRepository;
        this.weatherService = weatherService;
    }

    // Generate Weather Data every day for a specific Location
    @Scheduled(cron = "0 45 23 * * *")
    public void scheduleWeatherData(){

        // Get all the Locations from the Database
        List<Location> locationList = locationRepository.findAll();

        // Loop through all the Locations and get the Longitude and the Latitude of a specific Location
        for (Location location: locationList){

            String longitude = location.getLocLongitude();
            String latitude = location.getLocLatitude();

            if (latitude.isEmpty() && longitude.isEmpty()){
                throw new RuntimeException("Latitude and Longitude are missing");
            }

            weatherService.addWeatherData(location.getId(), longitude, latitude);

        }
    }

    // Monthly Payment of Employee
}
