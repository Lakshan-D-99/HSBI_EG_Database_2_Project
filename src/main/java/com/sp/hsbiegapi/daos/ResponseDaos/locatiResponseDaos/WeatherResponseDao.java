package com.sp.hsbiegapi.daos.ResponseDaos.locatiResponseDaos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WeatherResponseDao {

    private long id;
    private LocalDate todayDate;
    private double highestTemp;
    private double sunHours;
    private long locationId;

}
