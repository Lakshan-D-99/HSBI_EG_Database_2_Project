package com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DailyProductionResponseDao {

    private long id;
    private LocalDate curDate;
    private double dailyProdAmount;
    private long energySourceId;
}
