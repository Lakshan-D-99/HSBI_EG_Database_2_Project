package com.sp.hsbiegapi.apiServices.weatherApi;

import java.time.LocalDate;

public record WeatherApiResponse(double highestTemp, LocalDate curDate, double sunHours) {
}
