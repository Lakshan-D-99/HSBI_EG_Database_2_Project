package com.sp.hsbiegapi.repositories.energyRepositories;

import com.sp.hsbiegapi.daos.ResponseDaos.dashboardResponseDaos.DashboardEnergyProductionResponse;
import com.sp.hsbiegapi.models.energyModels.DailyProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyProductionRepository extends JpaRepository<DailyProduction,Long> {

    List<DailyProduction> getDailyProductionByEnergySourceId(long energySourceId);

    @Query(
            value = "SELECT SUM(daily_prod_amount) FROM dailyproduction;",
            nativeQuery = true
    )
    long getAllEnergyProduction();

    @Query(
            value = "SELECT cur_date, SUM(daily_prod_amount) AS total_production FROM dailyproduction GROUP BY cur_date ORDER BY cur_date limit 7;",
            nativeQuery = true
    )
    List<DashboardEnergyProductionResponse> getAllEnergyProductionWithDate();
}
