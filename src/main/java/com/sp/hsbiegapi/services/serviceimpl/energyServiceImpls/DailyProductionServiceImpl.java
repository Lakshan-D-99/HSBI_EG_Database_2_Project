package com.sp.hsbiegapi.services.serviceimpl.energyServiceImpls;

import com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao.DailyProductionRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.DailyProductionResponseDao;
import com.sp.hsbiegapi.models.energyModels.AlertSignal;
import com.sp.hsbiegapi.models.energyModels.DailyProduction;
import com.sp.hsbiegapi.models.energyModels.EnergySource;
import com.sp.hsbiegapi.repositories.energyRepositories.DailyProductionRepository;
import com.sp.hsbiegapi.repositories.energyRepositories.EnergySourceRepository;
import com.sp.hsbiegapi.services.energyServices.DailyProductionService;
import com.sp.hsbiegapi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DailyProductionServiceImpl implements DailyProductionService {

    private final DailyProductionRepository dailyProductionRepository;
    private final EnergySourceRepository energySourceRepository;

    @Autowired
    public DailyProductionServiceImpl(DailyProductionRepository dailyProductionRepository, EnergySourceRepository energySourceRepository) {
        this.dailyProductionRepository = dailyProductionRepository;
        this.energySourceRepository = energySourceRepository;
    }

    @Override
    public List<DailyProductionResponseDao> getAllProductionOfEnergySource(long energySourceId) {
        try {
            List<DailyProduction> dailyProductionList = dailyProductionRepository.getDailyProductionByEnergySourceId(energySourceId);

            List<DailyProductionResponseDao> dailyProductionResponseDaoList = new ArrayList<>();

            if (!dailyProductionList.isEmpty()){
                for (DailyProduction dailyProduction : dailyProductionList){
                    dailyProductionResponseDaoList.add(Mapper.conEntityToDao(dailyProduction));
                }

                return dailyProductionResponseDaoList;

            }else{
                System.out.println("Daily Production List is Empty");
            }

            return new ArrayList<DailyProductionResponseDao>();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public DailyProductionResponseDao getSingleDailyProd(long dailyProdId) {
        try{

            Optional<DailyProduction> dailyProduction = dailyProductionRepository.findById(dailyProdId);

            if (dailyProduction.isPresent()){
                return Mapper.conEntityToDao(dailyProduction.get());
            }

            System.out.println("Daily Production with the ID: " + dailyProdId + " does not exists");
            return new DailyProductionResponseDao();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return new DailyProductionResponseDao();
        }
    }

    @Override
    public void addDailyProductionToEnergySource(long energySourceId, DailyProductionRequestDao dailyProductionRequestDao) {

        try {

            // Check if the Energy Source exists or not
            Optional<EnergySource> energySource = energySourceRepository.findById(energySourceId);

            if (energySource.isPresent()){

                // Check if the Data has been passed or not
                if (dailyProductionRequestDao.getCurDate() != null && dailyProductionRequestDao.getDailyProdAmount() == 0.0){

                    DailyProduction dailyProduction = Mapper.conDaoToEntity(dailyProductionRequestDao);
                    dailyProduction.setEnergySource(energySource.get());

                    dailyProductionRepository.save(dailyProduction);

                    System.out.println("New Daily Production has been added to the Database");

                } else {
                    System.out.println("Can not add a Daily Production record to a energy source");
                }

            } else {
                System.out.println("Energy Source does not exists");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void removeDailyProductionOfEnergySource(long dailyProductionId) {
        try {
            Optional<DailyProduction> dailyProduction = dailyProductionRepository.findById(dailyProductionId);

            if (dailyProduction.isPresent()){
                dailyProductionRepository.delete(dailyProduction.get());
                System.out.println("DailyProduction with the ID:" + dailyProductionId + " has been removed");
            }else{
                System.out.println("DailyProduction with the ID:" + dailyProductionId + " does not exists");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
