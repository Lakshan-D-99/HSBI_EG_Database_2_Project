package com.sp.hsbiegapi.services.energyServices;

import com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao.AlertSignalRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.AlertSignalResponseDao;
import com.sp.hsbiegapi.models.energyModels.AlertSignal;

import java.util.List;

public interface AlertSignalService {

    // Get all the Alert Signals of a specific Energy Source
    List<AlertSignalResponseDao> getAllAlertSignaleOfEnergySource(long energySourceId);

    // Get a single Alert Signal of a specific Energy Source
    AlertSignalResponseDao getAlertSignalOfEnergySource(long alertSignalId);

    // Get the amount of all the Alerts
    String getAlertAmount();

    // Add a new Alert Signal to an Energy Source
    void addAlertSignalToEnergySource(long energySourceId, AlertSignalRequestDao alertSignalRequestDao);

    // Delete an Alert Signal from an Energy Source
    void removeAlertSignalFromEnergySource(long alertSignalId);
}
