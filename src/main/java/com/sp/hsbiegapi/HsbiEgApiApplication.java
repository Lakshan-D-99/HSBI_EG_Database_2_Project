package com.sp.hsbiegapi;

import com.sp.hsbiegapi.apiServices.koordinateApi.GeoCoordinateApiService;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HsbiEgApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(HsbiEgApiApplication.class, args);
    }

}
