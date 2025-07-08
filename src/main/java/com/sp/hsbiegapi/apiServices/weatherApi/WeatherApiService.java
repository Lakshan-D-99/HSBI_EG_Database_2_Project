package com.sp.hsbiegapi.apiServices.weatherApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class WeatherApiService {

    private final RestClient restClientWeather;
    private final ObjectMapper objectMapper;

    public WeatherApiService(RestClient restClient, ObjectMapper objectMapper) {
        this.restClientWeather = restClient;
        this.objectMapper = objectMapper;
    }

    // Get Weather Data from the Weather api
    public WeatherApiResponse getWeatherData(String latitude,String longitude) throws JsonProcessingException {

        // Generate the Url to get the Data
        String BASE_URL = "https://api.open-meteo.com/v1/forecast?latitude=" + latitude + "&longitude=" + longitude +"&daily=daylight_duration&hourly=temperature_2m&forecast_days=1";

        // Call the Url through the Rest Client
        String response = restClientWeather.get().uri(BASE_URL).retrieve().body(String.class);

        // Make a Json Tree using the Object Mapper and assign it into a JsonNode
        JsonNode node = objectMapper.readTree(response);

        // Get the Parent node
        JsonNode hourly = node.get("hourly");

        // Get the List of all the Temperatures
        JsonNode tempHours = hourly.get("temperature_2m");

        // Get the max Temp using the getHighestTemp method
        double maxTemp = getHighestTempValue(tempHours);

        // Get the current Date through the Json Response
        String date = node.get("daily").get("time").get(0).asText();

        // Get the Sun hours from the Response
        double sunSeconds = node.get("daily").get("daylight_duration").get(0).asDouble();

        // Convert Sun hours into real hours (from the Response, we get them in seconds instead of hours )
        double sunHours = conSecondsToHours(sunSeconds);

        // Lastly return a Weather Response back to the User
        return new WeatherApiResponse(maxTemp, LocalDate.parse(date), sunHours);
    }

    // Private Method to get the highest Temperature from a List of Temperatures
    private double getHighestTempValue(JsonNode listOfTemperatures){

        // Make an ArrayList of type double to store all the Temperature Data
        List<Double> temperatureList = new ArrayList<>();

        // Check if the passed in TemperatureList is an actual List
        if (listOfTemperatures.isArray()){

            // Loop through all the list
            for (JsonNode tempHour : listOfTemperatures){
                temperatureList.add(tempHour.asDouble());
            }

        }

        return temperatureList.stream().mapToDouble(i -> i).max().orElseThrow();
    }

    // Private Method to convert Sunlight Hours from seconds to Hours
    private double conSecondsToHours(double sunHoursInSeconds){
        return Math.floor(sunHoursInSeconds/3600);
    }

}
