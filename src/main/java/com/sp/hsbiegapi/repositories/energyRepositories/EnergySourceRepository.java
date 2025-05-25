package com.sp.hsbiegapi.repositories.energyRepositories;

import com.sp.hsbiegapi.models.energyModels.EnergySource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnergySourceRepository extends JpaRepository<EnergySource,Long> {
}
