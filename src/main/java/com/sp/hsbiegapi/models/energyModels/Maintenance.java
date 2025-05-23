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
@Table(name = "maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String maintenanceDetails;
    private double maintenanceCost;
    private String maintenanceStatus;
    private LocalDate maintenanceDate;

    /** Each maintenance Record belongs to a specific Energy Source */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "energysource_id")
    @JsonIgnore
    private EnergySource energySource;
}
