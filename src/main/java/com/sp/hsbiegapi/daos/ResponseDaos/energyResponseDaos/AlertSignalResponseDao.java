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
public class AlertSignalResponseDao {

    private long id;
    private LocalDate curDate;
    private String alarmType;
    private String alarmStatus;
    private long energySourceId;
}
