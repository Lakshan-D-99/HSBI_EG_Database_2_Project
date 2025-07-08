package com.sp.hsbiegapi.daos.ResponseDaos.joinResponses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocationEnergySourceResponse {

    private Long energySourceId;
    private String locationName;
    private String energySourceType;
    private Double energySourceCapacity;
    private String address;

}
