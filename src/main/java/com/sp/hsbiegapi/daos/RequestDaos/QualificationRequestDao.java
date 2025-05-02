package com.sp.hsbiegapi.daos.RequestDaos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QualificationRequestDao {

    private String qualificationType;
    private LocalDate qualificationValidDate;
    private long empId;
}
