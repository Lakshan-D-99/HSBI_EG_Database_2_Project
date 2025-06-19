package com.sp.hsbiegapi.daos.RequestDaos.locatiRequestDaos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocationRequestDao {

    private String locName;
    private String locAddress;
    private String locGeoDetails;
    private String locStatus;
    private int locCapacity;

}
