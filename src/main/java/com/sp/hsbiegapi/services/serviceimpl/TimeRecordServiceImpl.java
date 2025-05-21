package com.sp.hsbiegapi.services.serviceimpl;

import com.sp.hsbiegapi.daos.RequestDaos.TimeRecordRequests.TimeRecordRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.TimeRecordResponses.TimeRecordResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.TimeRecordResponses.WorkingHoursResponseDao;
import com.sp.hsbiegapi.models.emploModels.Employee;
import com.sp.hsbiegapi.models.emploModels.Payment;
import com.sp.hsbiegapi.models.emploModels.TimeRecord;
import com.sp.hsbiegapi.repositories.EmployeeRepository;
import com.sp.hsbiegapi.repositories.PaymentRepository;
import com.sp.hsbiegapi.repositories.TimeRecordRepository;
import com.sp.hsbiegapi.services.TimeRecordService;
import com.sp.hsbiegapi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimeRecordServiceImpl implements TimeRecordService {

    private final TimeRecordRepository timeRecordRepository;
    private final EmployeeRepository employeeRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public TimeRecordServiceImpl(TimeRecordRepository timeRecordRepository, EmployeeRepository employeeRepository,
            PaymentRepository paymentRepository) {
        this.timeRecordRepository = timeRecordRepository;
        this.employeeRepository = employeeRepository;
        this.paymentRepository = paymentRepository;
    }

    // -------------------- Model based Methods --------------------//

    @Override
    public List<TimeRecordResponseDao> getAllEmployeeTimeRecords(long employeeId) {
        try {

            List<TimeRecord> timeRecordList = timeRecordRepository.findAllTimeRecordsOfEmployee(employeeId);

            List<TimeRecordResponseDao> timeRecordResponseDaoList = new ArrayList<>();

            if (!timeRecordList.isEmpty()) {

                for (TimeRecord timeRecord : timeRecordList) {
                    timeRecordResponseDaoList.add(Mapper.conEntityToDao(timeRecord));
                }
            }

            return timeRecordResponseDaoList;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public TimeRecordResponseDao getSingleEmployeeTimeRecord(long recordId) {
        try {

            Optional<TimeRecord> timeRecord = timeRecordRepository.findById(recordId);


            return timeRecord.map(Mapper::conEntityToDao).orElseGet(TimeRecordResponseDao::new);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void addNewTimeRecordToEmployee(long employeeId, TimeRecordRequestDao timeRecordRequestDao) {

        try {

            // Find and Check if the Employee exists in the Database
            Optional<Employee> employee = employeeRepository.findById(employeeId);

            if (employee.isPresent()) {

                // Check if all the Required fields have been passed in
                if (timeRecordRequestDao.getWorkHours() > 0 &&
                        timeRecordRequestDao.getWorkDay() != null &&
                        timeRecordRequestDao.getJob() != null) {

                    TimeRecord timeRecord = Mapper.conDaoToEntity(timeRecordRequestDao);
                    timeRecord.setEmployee(employee.get());

                    timeRecordRepository.save(timeRecord);
                    System.out.println("New Time Record has been inserted into the Database");
                } else {
                    System.out.println("Provide all the required fields to continue");
                }

            } else {
                System.out.println("Employee does not exists");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void updateTimeRecordOfEmployee(long employeeId, long recordId, TimeRecordRequestDao timeRecordRequestDao) {

    }

    @Override
    public void deleteTimeRecordOfEmployee(long recordId) {

        try {

            Optional<TimeRecord> timeRecord = timeRecordRepository.findById(recordId);

            timeRecord.ifPresent(timeRecordRepository::delete);

            System.out.println("This Record does not exists");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // <!-- Internal Methods -->

    @Override
    public WorkingHoursResponseDao getEmployeeHoursBetweenTwoDates(long employeeId, LocalDate startDate,
            LocalDate endDate) {

        try {

            // Check if the Employee exists in the Database
            Optional<Employee> employee = employeeRepository.findById(employeeId);

            if (employee.isPresent()) {

                // Test Something //

                // Get all the Time Record of an Employee
                List<TimeRecord> timeRecordList = timeRecordRepository.findAllTimeRecordsOfEmployee(employeeId);

                List<TimeRecord> filteredTimeRecordList = timeRecordList.stream()
                        .filter(timeRecord ->
                                timeRecord.getWorkDay().isAfter(startDate) &&
                                        timeRecord.getWorkDay().isBefore(endDate)
                        )
                        .toList();

                double hours = filteredTimeRecordList.stream().mapToDouble(TimeRecord::getWorkHours).sum();

                //---------------//

            }

            return new WorkingHoursResponseDao();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;

        }

    }
}
