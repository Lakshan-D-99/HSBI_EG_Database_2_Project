package com.sp.hsbiegapi.apiServices.koordinateApi;

public record GeoCoordinateRequest(
        String locNumber,
        String streetName,
        String streetCode,
        String cityName,
        String country
) {

    public String generateGeoCoordinateRequest(){
        return locNumber + " " + streetName + ", " + streetCode + " " + cityName + ", " + country;
    }
}
