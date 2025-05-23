package com.sp.hsbiegapi.repositories;

import com.sp.hsbiegapi.models.energyModels.DailyProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyProductionRepository extends JpaRepository<DailyProduction,Long> {
}
