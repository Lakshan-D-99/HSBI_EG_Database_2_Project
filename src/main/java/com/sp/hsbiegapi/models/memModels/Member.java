package com.sp.hsbiegapi.models.memModels;


import com.sp.hsbiegapi.models.energyModels.EnergySource;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String memberName;
    @Column(unique = true)
    private String memberMatNumber;
    @Column(unique = true)
    private String memberEmail;
    private double memberContribution;
    private String memberStatus;
    private LocalDate memberJoinDate;
    private String memberPaymentType;

    /**
     * The Relationship between Member and Energy Source is a Many-To-Many Relationship. So a Member can join to one to or many Energy Sources, whereas an Energy Source can have multiple Members.
     */
    @ManyToMany
    @JoinTable(
            name = "member_energysource",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "energysource_id")
    )
    Set<EnergySource> energySourceSet = new HashSet<>();
}
