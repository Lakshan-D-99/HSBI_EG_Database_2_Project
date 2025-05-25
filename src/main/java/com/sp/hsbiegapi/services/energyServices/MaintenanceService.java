package com.sp.hsbiegapi.services.energyServices;

import com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao.MaintenanceRequestsDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.MaintenanceResponseDao;

import java.util.List;

public interface MaintenanceService {

    // Get all the Maintenance Details of a specific Energy Source
    List<MaintenanceResponseDao> getAllMaintenanceOfEnergySource(long energySourceId);

    // Get a single Maintenance Record
    MaintenanceResponseDao getSingleMaintenanceRecord(long maintenanceId);

    // Add a Maintenance Record into an Energy Source
    void addMaintenanceRecord(long energySourceId, MaintenanceRequestsDao maintenanceRequestsDao);

    // Remove a Maintenance Record from an Energy Source
    void removeMaintenanceRecord(long maintenanceId);
}
