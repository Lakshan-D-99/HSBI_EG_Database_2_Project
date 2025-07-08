package com.sp.hsbiegapi.services.serviceimpl.locatiServiceImpls;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sp.hsbiegapi.apiServices.weatherApi.WeatherApiResponse;
import com.sp.hsbiegapi.apiServices.weatherApi.WeatherApiService;
import com.sp.hsbiegapi.daos.ResponseDaos.locatiResponseDaos.WeatherResponseDao;
import com.sp.hsbiegapi.models.locModels.Location;
import com.sp.hsbiegapi.models.locModels.WeatherData;
import com.sp.hsbiegapi.repositories.locatiRepositories.LocationRepository;
import com.sp.hsbiegapi.repositories.locatiRepositories.WeatherRepository;
import com.sp.hsbiegapi.services.locatiServices.WeatherService;
import com.sp.hsbiegapi.utils.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository weatherRepository;
    private final LocationRepository locationRepository;
    private final WeatherApiService weatherApiService;

    public WeatherServiceImpl(WeatherRepository weatherRepository, LocationRepository locationRepository, WeatherApiService weatherApiService) {
        this.weatherRepository = weatherRepository;
        this.locationRepository = locationRepository;
        this.weatherApiService = weatherApiService;
    }

    @Override
    public List<WeatherResponseDao> getAllWeatherData() {

        try {
            // Get all the Weather Data from the Database
            List<WeatherData> weatherDataList = weatherRepository.findAll();

            // Create a Weather Response Dao list to store Weather Data
            List<WeatherResponseDao> weatherResponseDaoList = new ArrayList<>();

            // Convert Weather Data and add it to the Weather Response Dao List
            weatherDataList.forEach(weatherData -> weatherResponseDaoList.add(Mapper.conEntityToDao(weatherData)));

            // Return the List of Weather Data
            return weatherResponseDaoList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<WeatherResponseDao> getAllWeatherDataOfLocation(long locationId) {

        try {
            // Get the Location based on the passed in Location id
            Location location = locationRepository.findById(locationId).orElseThrow();

            // Create a List to store all the Weather Data of the Location as Response Data
            List<WeatherResponseDao> weatherResponseDaoList = new ArrayList<>();

            // Convert Weather Data into Response Data
            location.getWeatherDataList().forEach(weatherData -> weatherResponseDaoList.add(Mapper.conEntityToDao(weatherData)));

            // Return the Weather Data
            return weatherResponseDaoList;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void addWeatherData(long locationId, String longitude, String latitude) {

        try {
            // Get the Location based on the passed in Location id
            Location location = locationRepository.findById(locationId).orElseThrow();

            WeatherApiResponse weatherApiResponse;

            // Use WeatherServiceApi to generate Weather Data
            try {
                weatherApiResponse = weatherApiService.getWeatherData(location.getLocLatitude(), location.getLocLongitude());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

            // Create a Weather Object to save to the Database
            WeatherData weatherData = new WeatherData();
            weatherData.setHighestTemp(weatherApiResponse.highestTemp());
            weatherData.setCurDate(weatherApiResponse.curDate());
            weatherData.setSunHours(weatherApiResponse.sunHours());
            weatherData.setLocation(location);

            // Lastly, save it to the Database
            weatherRepository.save(weatherData);

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}
