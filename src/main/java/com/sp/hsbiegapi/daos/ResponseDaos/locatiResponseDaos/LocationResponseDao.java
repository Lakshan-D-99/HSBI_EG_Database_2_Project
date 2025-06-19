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
public class LocationResponseDao {

    private long id;
    private String locName;
    private String locAddress;
    //private String locGeoDetails;
    private String locLatitude;
    private String locLongitude;
    private String locStatus;
    private int locCapacity;
    private LocalDate startDate;
}
