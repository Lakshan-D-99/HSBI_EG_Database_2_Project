package com.sp.hsbiegapi.services.serviceimpl.energyServiceImpls;

import com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao.MaintenanceRequestsDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.MaintenanceResponseDao;
import com.sp.hsbiegapi.models.energyModels.EnergySource;
import com.sp.hsbiegapi.models.energyModels.Maintenance;
import com.sp.hsbiegapi.repositories.energyRepositories.EnergySourceRepository;
import com.sp.hsbiegapi.repositories.energyRepositories.MaintenanceRepository;
import com.sp.hsbiegapi.services.energyServices.MaintenanceService;
import com.sp.hsbiegapi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final EnergySourceRepository energySourceRepository;

    @Autowired
    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository, EnergySourceRepository energySourceRepository) {
        this.maintenanceRepository = maintenanceRepository;
        this.energySourceRepository = energySourceRepository;
    }


    @Override
    public List<MaintenanceResponseDao> getAllMaintenanceRecords() {
        try {

            List<MaintenanceResponseDao> maintenanceResponseDaoList = new ArrayList<>();

            List<Maintenance> maintenanceList = maintenanceRepository.findAll();

            if (!maintenanceList.isEmpty()){

                for (Maintenance maintenance : maintenanceList){
                    maintenanceResponseDaoList.add(Mapper.conEntityToDao(maintenance));
                }

                return maintenanceResponseDaoList;
            }

            System.out.println("There are no Maintenance Records");

            return new ArrayList<>();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<MaintenanceResponseDao> getAllMaintenanceOfEnergySource(long energySourceId) {
        try {

            List<MaintenanceResponseDao> maintenanceResponseDaoList = new ArrayList<>();

            List<Maintenance> maintenanceList = maintenanceRepository.findAllByEnergySourceId(energySourceId);

            if (!maintenanceList.isEmpty()){

                for (Maintenance maintenance : maintenanceList){
                    maintenanceResponseDaoList.add(Mapper.conEntityToDao(maintenance));
                }

                return maintenanceResponseDaoList;
            }

            System.out.println("There are no Maintenance Records for this Energy Source with the ID: " + energySourceId);

            return new ArrayList<>();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public MaintenanceResponseDao getSingleMaintenanceRecord(long maintenanceId) {
        try {

            Optional<Maintenance> maintenance = maintenanceRepository.findById(maintenanceId);

            if (maintenance.isPresent()){

                return Mapper.conEntityToDao(maintenance.get());
            }

            System.out.println("Maintenance with the ID: " + maintenanceId + " does not exists");

            return new MaintenanceResponseDao();

        } catch (Exception e){
            System.out.println(e.getMessage());
            return new MaintenanceResponseDao();
        }
    }

    @Override
    public void addMaintenanceRecord(long energySourceId, MaintenanceRequestsDao maintenanceRequestsDao) {
        try {

            // Get the Energy Source and check if the Energy Source exists in the DB
            Optional<EnergySource> energySource = energySourceRepository.findById(energySourceId);

            if (energySource.isPresent()) {

                // Check if the Maintenance Data has been passed correctly or not
                if (maintenanceRequestsDao.getMaintenanceDetails() != null
                        && maintenanceRequestsDao.getMaintenanceCost() == 0.0
                        && maintenanceRequestsDao.getMaintenanceStatus() != null
                        && maintenanceRequestsDao.getMaintenanceDate() != null) {

                    Maintenance maintenance = Mapper.conDaoToEntity(maintenanceRequestsDao);
                    maintenance.setEnergySource(energySource.get());

                    maintenanceRepository.save(maintenance);

                    System.out.println("New Maintenance record has been added to the Energy Source : " + energySourceId);

                }
                System.out.println("Maintenance Record can not be added");

            }
            System.out.println("Energy Source does not exists");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeMaintenanceRecord(long maintenanceId) {
        try {
            Optional<Maintenance> maintenance = maintenanceRepository.findById(maintenanceId);

            if (maintenance.isPresent()){
                maintenanceRepository.delete(maintenance.get());
                System.out.println("Maintenance Record with the ID: " + maintenanceId + " has been removed");
            }

            System.out.println("Error deleting the Maintenance Record");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
