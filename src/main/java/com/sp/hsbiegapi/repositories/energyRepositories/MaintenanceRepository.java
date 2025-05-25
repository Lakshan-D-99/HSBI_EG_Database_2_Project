package com.sp.hsbiegapi.repositories.energyRepositories;

import com.sp.hsbiegapi.models.energyModels.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance,Long> {
}
