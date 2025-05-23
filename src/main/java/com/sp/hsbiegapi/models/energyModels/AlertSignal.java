package com.sp.hsbiegapi.models.energyModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "alertsignal")
public class AlertSignal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate curDate;
    private String alarmType;
    private String alarmStatus;

    /**
     * Many Energy Sources can have multiple Alert Signals, therefor many to one relationship between Alert signals and Energy sources
     */
    @ManyToOne
    @JoinColumn(name = "energysource_id")
    @JsonIgnore
    private EnergySource energySource;
}
