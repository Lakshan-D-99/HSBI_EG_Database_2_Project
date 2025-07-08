package com.sp.hsbiegapi.models.locModels;

import com.sp.hsbiegapi.models.energyModels.EnergySource;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    private String locName;
    private String locAddress;
    private String locLatitude;
    private String locLongitude;
    private String locStatus;
    private int locCapacity;
    private LocalDate locStartDate;

    /**
     * Each Location should be able to store daily Weather data, therefor we will have a one-to-many relationship between Location and Weather
     */
    @OneToMany(
            mappedBy = "location",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private List<WeatherData> weatherDataList = new ArrayList<>();

    @ManyToMany(mappedBy = "locationSet")
    private Set<EnergySource> energySourceSet = new HashSet<>();

}
