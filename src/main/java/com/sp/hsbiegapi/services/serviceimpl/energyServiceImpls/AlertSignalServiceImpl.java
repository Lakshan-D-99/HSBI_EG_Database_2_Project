package com.sp.hsbiegapi.services.serviceimpl.energyServiceImpls;

import com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao.AlertSignalRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.AlertSignalResponseDao;
import com.sp.hsbiegapi.models.energyModels.AlertSignal;
import com.sp.hsbiegapi.models.energyModels.EnergySource;
import com.sp.hsbiegapi.repositories.energyRepositories.AlertSignalRepository;
import com.sp.hsbiegapi.repositories.energyRepositories.EnergySourceRepository;
import com.sp.hsbiegapi.services.energyServices.AlertSignalService;
import com.sp.hsbiegapi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlertSignalServiceImpl implements AlertSignalService {

    private final AlertSignalRepository alertSignalRepository;
    private final EnergySourceRepository energySourceRepository;

    @Autowired
    public AlertSignalServiceImpl(AlertSignalRepository alertSignalRepository, EnergySourceRepository energySourceRepository) {
        this.alertSignalRepository = alertSignalRepository;
        this.energySourceRepository = energySourceRepository;
    }

    @Override
    public List<AlertSignalResponseDao> getAllAlertSignaleOfEnergySource(long energySourceId) {
       return null;
    }

    @Override
    public AlertSignalResponseDao getAlertSignalOfEnergySource(long alertSignalId) {
        try {
            Optional<AlertSignal> alertSignal = alertSignalRepository.findById(alertSignalId);

            if (alertSignal.isPresent()){
                return Mapper.conEntityToDao(alertSignal.get());
            }

            return new AlertSignalResponseDao();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new AlertSignalResponseDao();
        }
    }

    @Override
    public void addAlertSignalToEnergySource(long energySourceId, AlertSignalRequestDao alertSignalRequestDao) {

        try {

            // Get the EnergySource through the passed in energySourceId
            Optional<EnergySource> energySource = energySourceRepository.findById(energySourceId);

            if (energySource.isPresent()){

                // Check if the Alert Signal Data has been passed correctly or not
                if (alertSignalRequestDao.getCurDate() != null && !(alertSignalRequestDao.getAlarmType().isEmpty()) && !(alertSignalRequestDao.getAlarmStatus().isEmpty())){

                    // Create an Alert Signal Object and store it into the Database
                    AlertSignal alertSignal = Mapper.conDaoToEntity(alertSignalRequestDao);
                    alertSignal.setEnergySource(energySource.get());

                    alertSignalRepository.save(alertSignal);

                    System.out.println("An Alert system has occurred from the Energy Source: " + energySourceId);
                }else {
                    System.out.println("Alert Signal was not able to made, because of missing Data");
                }

            }else {
                System.out.println("Energy Source doesn't exists, AlertSignal was not able to made");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void removeAlertSignalFromEnergySource(long alertSignalId) {

        try {

            // Get the Alert Signal
            Optional<AlertSignal> alertSignal = alertSignalRepository.findById(alertSignalId);

            if (alertSignal.isPresent()){
                alertSignalRepository.delete(alertSignal.get());
                System.out.println("Alert Signal with the Id: " + alertSignalId + " has been removed");
            }else {
                System.out.println("Alert Signal does not exists");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
