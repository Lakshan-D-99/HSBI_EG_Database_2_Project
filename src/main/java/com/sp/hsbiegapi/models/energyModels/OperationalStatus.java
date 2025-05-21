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
@Table(name = "operation_status")
public class OperationalStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate currentDate;
    private String opStatus;

    /* *
     *  Each Operational Status belongs to a specific Energy Source
     */
    @ManyToOne
    @JoinColumn(name = "energy_source_id")
    @JsonIgnore
    private EnergySource energySource;

}
