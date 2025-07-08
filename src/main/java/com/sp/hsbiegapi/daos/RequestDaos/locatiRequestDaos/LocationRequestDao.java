package com.sp.hsbiegapi.daos.RequestDaos.locatiRequestDaos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocationRequestDao {

    private String locName;
    private String locAddress;
    private String locLatitude;
    private String locLongitude;
    private String locStatus;
    private int locCapacity;
    private LocalDate startDate;

}
