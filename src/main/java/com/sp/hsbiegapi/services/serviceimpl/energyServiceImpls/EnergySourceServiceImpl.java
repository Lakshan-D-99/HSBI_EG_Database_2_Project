package com.sp.hsbiegapi.services.serviceimpl.energyServiceImpls;

import com.sp.hsbiegapi.models.energyModels.EnergySource;
import com.sp.hsbiegapi.repositories.energyRepositories.EnergySourceRepository;
import com.sp.hsbiegapi.services.energyServices.EnergySourceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EnergySourceServiceImpl implements EnergySourceService {

    private final EnergySourceRepository energySourceRepository;

    @Autowired
    public EnergySourceServiceImpl(EnergySourceRepository energySourceRepository) {
        this.energySourceRepository = energySourceRepository;
    }

    @Override
    public List<EnergySource> getAllEnergySources() {
        return List.of();
    }

    @Override
    public EnergySource getEnergySource(long energySourceId) {
        return null;
    }

    @Override
    public void addNewEnergySource(EnergySource energySource) {

    }

    @Override
    public void updateEnergySource(long energySourceId, EnergySource energySource) {

    }

    @Override
    public void deleteEnergySource(long energySourceId) {

    }
}
