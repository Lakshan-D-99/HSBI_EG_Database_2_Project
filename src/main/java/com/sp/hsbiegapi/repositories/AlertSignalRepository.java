package com.sp.hsbiegapi.repositories;

import com.sp.hsbiegapi.models.energyModels.AlertSignal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertSignalRepository extends JpaRepository<AlertSignal,Long> {
}
