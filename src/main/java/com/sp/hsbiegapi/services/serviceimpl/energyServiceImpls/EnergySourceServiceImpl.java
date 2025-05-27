package com.sp.hsbiegapi.services.serviceimpl.energyServiceImpls;

import com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao.EnergySourceRequestsDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.EnergySourceResponseDao;
import com.sp.hsbiegapi.models.energyModels.EnergySource;
import com.sp.hsbiegapi.repositories.energyRepositories.EnergySourceRepository;
import com.sp.hsbiegapi.services.energyServices.EnergySourceService;
import com.sp.hsbiegapi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnergySourceServiceImpl implements EnergySourceService {

    private final EnergySourceRepository energySourceRepository;

    @Autowired
    public EnergySourceServiceImpl(EnergySourceRepository energySourceRepository) {
        this.energySourceRepository = energySourceRepository;
    }

    @Override
    public List<EnergySourceResponseDao> getAllEnergySources() {
        try {
            List<EnergySourceResponseDao> energySourceResponseDaoList = new ArrayList<>();

            List<EnergySource> allEnergySources = energySourceRepository.findAll();

            if (!allEnergySources.isEmpty()) {

                for (EnergySource energySource : allEnergySources) {
                    energySourceResponseDaoList.add(Mapper.conEntityToDao(energySource));
                }

                return energySourceResponseDaoList;
            }

            System.out.println("There are no Energy Sources to display");

            return energySourceResponseDaoList;
        } catch (Exception e) {
            System.out.println("Error getting all the Energy Sources" + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public EnergySourceResponseDao getEnergySource(long energySourceId) {
        try {

            Optional<EnergySource> energySource = energySourceRepository.findById(energySourceId);

            if (energySource.isPresent()) {
                return Mapper.conEntityToDao(energySource.get());
            }

            System.out.println("Energy Source does not exists");

            return new EnergySourceResponseDao();


        } catch (Exception e) {
            System.out.println("Error getting the Energy Source" + e.getMessage());
            return new EnergySourceResponseDao();
        }
    }

    @Override
    public void addNewEnergySource(EnergySourceRequestsDao energySourceRequestsDao) {
        try {

            // Check if all the necessary Data has been passed in correctly to create a new Energy Source and add it to the Database
            if (energySourceRequestsDao.getEnergyCapacity() != 0.0 && !(energySourceRequestsDao.getEnergyType().isEmpty()) && energySourceRequestsDao.getStartDate() != null) {

                EnergySource energySource = Mapper.conDaoToEntity(energySourceRequestsDao);
                energySource.setEnergyAvailable(0.0);

                energySourceRepository.save(energySource);

                System.out.println("New Energy Source has been added and saved to the Database");

            }

            System.out.println("Error adding a new Energy Source, because of the missing Information");


        } catch (Exception e) {
            System.out.println("Error adding a new Energy Source: " + e.getMessage());
        }
    }

    @Override
    public void updateEnergySource(long energySourceId, EnergySourceRequestsDao energySourceRequestsDao) {

    }

    @Override
    public void deleteEnergySource(long energySourceId) {
        try {
            Optional<EnergySource> isEnergySource = energySourceRepository.findById(energySourceId);

            if (isEnergySource.isPresent()){
                energySourceRepository.delete(isEnergySource.get());
            }

            System.out.println("Energy Source does not exist");
        } catch (Exception e){
            System.out.println("Error removing the Energy Source: " + e.getMessage());
        }

    }
}
