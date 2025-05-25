package com.sp.hsbiegapi.services.energyServices;

import com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao.OperationalStatusRequestsDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.MaintenanceResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.OperationalStatusResponseDao;

import java.util.List;

public interface OperationalStatusService {

    // Get all the Operational Status of a specific Energy Source
    List<OperationalStatusResponseDao> getAllOpsOfEnergySource(long energySourceId);

    // Get a single Maintenance Record detail
    MaintenanceResponseDao getSingleMaintenanceRecord(long maintenanceId);

    // Add an Operational Status to an Energy Source
    void addOpsToEnergySource(long energySourceId, OperationalStatusRequestsDao operationalStatusRequestsDao);

    // Remove an Operational Status from an Energy Source
    void removeEnergySourceFromOps(long opId);
}
