package com.sp.hsbiegapi.daos.ResponseDaos.dashboardResponseDaos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;


@NoArgsConstructor
@Getter
@Setter
public class DashboardEnergyProductionResponse {

    private LocalDate curDate;
    private Double dailyProdAmount;

    public DashboardEnergyProductionResponse(Date curDate, Double dailyProdAmount) {
        this.curDate = curDate.toLocalDate();
        this.dailyProdAmount = dailyProdAmount;
    }
}
