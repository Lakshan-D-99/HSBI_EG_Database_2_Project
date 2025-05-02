package com.sp.hsbiegapi.repositories;

import com.sp.hsbiegapi.models.emploModels.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimeRecordRepository extends JpaRepository<TimeRecord,Long> {

    @Query("SELECT t FROM Qualification t WHERE t.employee.id = :employeeId")
    List<TimeRecord> findAllTimeRecordsOfEmployee(long employeeId);

    @Query("SELECT SUM(t.workHours) FROM TimeRecord t WHERE t.employee.id = :employeeId AND t.workDay BETWEEN :startDate AND :endDate")
    Optional<Double> getTotalPaymentBetweenDates(
            @Param("employeeId") Long employeeId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
