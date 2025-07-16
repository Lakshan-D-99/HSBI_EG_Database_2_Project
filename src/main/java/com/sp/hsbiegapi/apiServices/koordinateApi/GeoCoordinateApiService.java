package com.sp.hsbiegapi.apiServices.koordinateApi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

@Service
public class GeoCoordinateApiService implements CommandLineRunner {

    @Value("${GEO_COORDINATE_API}")
    private String API_KEY; // This is a fake key, so it wont work

    private String BASE_URL = "https://api.geoapify.com/v1/geocode/search?text=";

    private final ObjectMapper objectMapper;
    private final RestClient restClient;

    @Autowired
    public GeoCoordinateApiService(ObjectMapper objectMapper, RestClient restClient) {
        this.objectMapper = objectMapper;
        this.restClient = RestClient.builder().baseUrl(BASE_URL).build();
    }

    public GeoCoordinateResponse getData(String address) throws JsonProcessingException {

        // Generate the Url to get the Data from the Geocoordinate Api
        String urlString = BASE_URL + "" + address + "&format=json&apiKey=" + this.API_KEY + "";

        // Store the Response as String
        String response = restClient.get().uri(urlString).retrieve().body(String.class);

        // Using JsonNode we can create json Tree out of our Json Response
        JsonNode node = objectMapper.readTree(response);

        // First, get the Parent Array or Object -> in our case it is the "results" Array.
        JsonNode parentNode = node.get("results");

        // Get the longitude and Latitude from the Json Response
        JsonNode longitude = parentNode.get(0).get("lon");
        JsonNode latitude = parentNode.get(0).get("lat");

//        System.out.println(longitude);
//        System.out.println(latitude);

        return new GeoCoordinateResponse(latitude.toString(), longitude.toString());

    }

    @Override
    public void run(String... args) throws Exception {
        getData("Südstraße 10, 32602 Vlotho, Germany");
    }
}
