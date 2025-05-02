package com.sp.hsbiegapi.models.energyModels;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long locationId;
    private String locationName;
    private String locationAddress;
    private String locationGeoDetails;
    private String locationStatus;
    private int locationCapacity;
    private LocalDate locationStartDate;

}
