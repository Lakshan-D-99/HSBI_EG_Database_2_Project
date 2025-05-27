package com.sp.hsbiegapi.controllers.energyControllers;

import com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao.MaintenanceRequestsDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.MaintenanceResponseDao;
import com.sp.hsbiegapi.services.energyServices.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/energy-source/maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @Autowired
    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    // Get all the Maintenance records in the Database
    public List<MaintenanceResponseDao> getAllMaintenance(){
        return null;
    }

    // Get all the Maintenance records of a specific Energy Source
    @GetMapping("/engId={energyId}/all")
    public List<MaintenanceResponseDao> getAllMaintenanceOfEnergySource(@PathVariable long energyId){
        return maintenanceService.getAllMaintenanceOfEnergySource(energyId);
    }

    // Get a single Maintenance record based on the passed in MaintenanceId
    @GetMapping("/mainId={mId}")
    public MaintenanceResponseDao getMaintenance(@PathVariable long mId){
        return maintenanceService.getSingleMaintenanceRecord(mId);
    }

    // Add a new Maintenance Record to an Energy Source
    @PostMapping("/engId={energyId}/new-main")
    public String addMaintenance(@PathVariable long energyId, @RequestBody MaintenanceRequestsDao maintenanceRequestsDao){
        maintenanceService.addMaintenanceRecord(energyId, maintenanceRequestsDao);
        return "New Maintenance Record has been added to the Energy Source with the Id: " + energyId + ".";
    }

    // Update an existing Maintenance record in the Database

    // Delete an existing Maintenance record in the Database
    @DeleteMapping("/mId={mainId}")
    public String deleteMaintenance(@PathVariable long mainId){
        maintenanceService.removeMaintenanceRecord(mainId);
        return "Maintenance Record with the Maintenance Id: " + mainId + " has been removed";
    }

}
