package com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DailyProductionRequestDao {

    private LocalDate curDate;
    private double dailyProdAmount;
    private long energySourceId;
}
