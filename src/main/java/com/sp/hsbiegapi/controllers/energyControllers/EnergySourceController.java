package com.sp.hsbiegapi.controllers.energyControllers;

import com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao.EnergySourceRequestsDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.EnergySourceResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.joinResponses.LocationEnergySourceResponse;
import com.sp.hsbiegapi.services.energyServices.EnergySourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api_v1/energy-sources")
public class EnergySourceController {

    private final EnergySourceService energySourceService;

    @Autowired
    public EnergySourceController(EnergySourceService energySourceService) {
        this.energySourceService = energySourceService;
    }

    // Get all the EnergySources in the Database
    @GetMapping("/all")
    public List<EnergySourceResponseDao> getAllEnergySources(){
        return energySourceService.getAllEnergySources();
    }

    // Get a Single EnergySource from the Database
    @GetMapping("/{eId}")
    public EnergySourceResponseDao getEnergySource(@PathVariable long eId){
        return energySourceService.getEnergySource(eId);
    }

    // Get All Energy Sources with their Locations
    @GetMapping("/all-with-locations")
    public List<LocationEnergySourceResponse> getAllEnergySourcesWithLocations(){
        return energySourceService.getAllEnergySourcesAndLocations();
    }

    // Add a new Energy Source into the Database
    @PostMapping("/new")
    public String addEnergySource(@RequestBody EnergySourceRequestsDao energySourceRequestsDao){
        energySourceService.addNewEnergySource(energySourceRequestsDao);
        return "New Energy Source has been added to the Database";
    }

    // Update an existing Energy Source in the Database

    // Delete an existing Energy Source in the Database
    @DeleteMapping("/{eId}")
    public String deleteEnergySource(@PathVariable long eId){
        energySourceService.deleteEnergySource(eId);
        return "Energy Source with the ID: " + eId + " has been removed from the Database";
    }
}
