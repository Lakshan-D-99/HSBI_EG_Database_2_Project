package com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QualificationResponseDao {

    private long id;
    private String qualificationType;
    private LocalDate qualificationValidDate;
    private long empId;
}
