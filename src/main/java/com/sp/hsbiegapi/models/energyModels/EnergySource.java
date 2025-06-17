package com.sp.hsbiegapi.models.energyModels;

import com.sp.hsbiegapi.models.emploModels.Employee;
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
@Table(name = "energysource")
public class EnergySource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double energyCapacity;
    private String energyType;
    private double energyAvailable;
    private LocalDate startDate;

    /**
     * One Energy Source can have multiple Maintenance Record and a Maintenance Record can not
     * exits without an Energy Sourcer
     *
     * So the Relationship between Energy Source and Maintenance Records will be one to many, because
     * one Energy Source can have multiple Maintenance Records */
    @OneToMany(mappedBy = "energySource", cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Maintenance> maintenanceList = new ArrayList<>();

    /**
     * An Energy Source have to check and store production details daily, so for that we have another one to many relationship
     * where one energy source can have multiple Dailyproductions*/
    @OneToMany(mappedBy = "energySource", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DailyProduction> dailyProductionList = new ArrayList<>();

    /**
     * An Energy Source can also send one or more Alert signals if they have a problem or, therfore the
     * AlertSignal Table will stor all the Records of different Alert Signals.
     */
    @OneToMany(mappedBy = "energySource",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<AlertSignal> alertSignalList = new ArrayList<>();

    /**
     * An Energy Source can have mutliple status. It could be working, under maintenance...
     * To Store them we link the OperationalStatus Table
     */
    @OneToMany(mappedBy = "energySource",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<OperationalStatus> operationalStatusList = new ArrayList<>();

    /**
     * One Employee can assigned to multiple Energy Sources and vice versa. Therfore we have m to n relationship
     * between Employees and Energy Sources.
     */
    @ManyToMany
    @JoinTable(
            name = "energysource_employee",
            joinColumns = @JoinColumn(name = "energysource_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")

    )
    private Set<Employee> employeeSet = new HashSet<>();

    // Add an Employee into a Project
    public void addEmployee(Employee employee){
        employeeSet.add(employee);
        employee.getEnergySourceSet().add(this);
    }

    // Remove an Employee from a Project
    public void removeEmployee(Employee employee){
        employeeSet.remove(employee);
        employee.getEnergySourceSet().remove(this);
    }
}
