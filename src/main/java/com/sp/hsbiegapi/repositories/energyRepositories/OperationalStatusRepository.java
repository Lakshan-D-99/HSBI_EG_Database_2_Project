package com.sp.hsbiegapi.repositories.energyRepositories;

import com.sp.hsbiegapi.models.energyModels.OperationalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationalStatusRepository extends JpaRepository<OperationalStatus,Long> {
}
