package com.sp.hsbiegapi.utils;

import com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos.EmployeeRequestDao;
import com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos.PaymentRequestDao;
import com.sp.hsbiegapi.daos.RequestDaos.energyRequestsDao.*;
import com.sp.hsbiegapi.daos.RequestDaos.locatiRequestDaos.LocationRequestDao;
import com.sp.hsbiegapi.daos.RequestDaos.memberRequestsDao.MemberRequestDao;
import com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos.QualificationRequestDao;
import com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos.TimeRecordRequests.TimeRecordRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.EmployeeResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.energyResponseDaos.*;
import com.sp.hsbiegapi.daos.ResponseDaos.locatiResponseDaos.LocationResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.memberResponseDaos.MemberResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.PaymentResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.QualificationResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.TimeRecordResponses.TimeRecordResponseDao;
import com.sp.hsbiegapi.models.emploModels.Employee;
import com.sp.hsbiegapi.models.emploModels.Payment;
import com.sp.hsbiegapi.models.emploModels.Qualification;
import com.sp.hsbiegapi.models.emploModels.TimeRecord;
import com.sp.hsbiegapi.models.energyModels.*;
import com.sp.hsbiegapi.models.locModels.Location;
import com.sp.hsbiegapi.models.memModels.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This Class will be used to convert Entities into Dao s and vice versa
 */
public class Mapper {

    // Convert an EmployeeRequestDao into an Employee Object
    public static Employee conDaoToEntity(EmployeeRequestDao employeeRequestDao) {
        Employee employee = new Employee();
        employee.setEmpName(employeeRequestDao.getEmpName());
        employee.setEmpEmail(employeeRequestDao.getEmpEmail());
        employee.setEmpContactNum(employeeRequestDao.getEmpContactNumber());
        employee.setEmpPosition(employeeRequestDao.getEmpPosition());
        //employee.setEmpStartDate(employeeRequestDao.getEmpStartDate());
        employee.setEmpInfo(employeeRequestDao.getEmpInfo());
        return employee;
    }

    // Convert an Employee Object into an EmployeeResponseDao,as we are sending them to the Frontend
    public static EmployeeResponseDao conEntityToDao(Employee employee){
        EmployeeResponseDao employeeResponseDao = new EmployeeResponseDao();
        employeeResponseDao.setEmpId(employee.getId());
        employeeResponseDao.setEmpName(employee.getEmpName());
        employeeResponseDao.setEmpEmail(employee.getEmpEmail());
        employeeResponseDao.setEmpContactNumber(employee.getEmpContactNum());
        employeeResponseDao.setEmpPosition(employee.getEmpPosition());
        employeeResponseDao.setEmpStartDate(employee.getEmpStartDate());

        // Check if the Employee has Payments and assign them to the Employee Response Object
        List<PaymentResponseDao> paymentResponseDaoList = new ArrayList<>();

        if (employee.getPaymentList() != null){
            for (Payment payment : employee.getPaymentList()){
                paymentResponseDaoList.add(conEntityToDao(payment));
            }
        }

        employeeResponseDao.setPaymentResponseDaoList(paymentResponseDaoList);

        // Check it the Employee has Qualifications and assign them to the Employee Response Object
        List<QualificationResponseDao> qualificationResponseDaoList = new ArrayList<>();

        if (employee.getQualificationList() != null){
            for (Qualification qualification : employee.getQualificationList()){
                qualificationResponseDaoList.add(conEntityToDao(qualification));
            }
        }

        employeeResponseDao.setQualificationResponseDaoList(qualificationResponseDaoList);

        // Check if the Employee has time keeping and assign them to the Employee Response Object
        List<TimeRecordResponseDao> timeRecordResponseDaoList = new ArrayList<>();

        if (employee.getTimeRecordList() != null){
            for (TimeRecord timeRecord : employee.getTimeRecordList()){
                timeRecordResponseDaoList.add(conEntityToDao(timeRecord));
            }
        }

        employeeResponseDao.setTimeRecordResponseDaoList(timeRecordResponseDaoList);

        return employeeResponseDao;
    }

    // Convert Payment Entity into a Payment ResponseDao
    public static PaymentResponseDao conEntityToDao(Payment payment){
        PaymentResponseDao paymentResponseDao = new PaymentResponseDao();
        paymentResponseDao.setId(payment.getId());
        paymentResponseDao.setPaymentAmount(payment.getPaymentAmount());
        paymentResponseDao.setPaymentType(payment.getPaymentType());
        paymentResponseDao.setPaymentInvoiceNum(payment.getPaymentInvoiceNum());
        paymentResponseDao.setPaymentDate(payment.getPaymentDate());
        paymentResponseDao.setEmpId(payment.getEmployee().getId());
        return paymentResponseDao;
    }

    // Convert a PaymentRequsetDao into a Payment Object
    public static Payment conDaoToEntity(PaymentRequestDao paymentRequestDao){
        Payment payment = new Payment();
        payment.setPaymentAmount(paymentRequestDao.getPaymentAmount());
        payment.setPaymentType(paymentRequestDao.getPaymentType());
        payment.setPaymentInvoiceNum(paymentRequestDao.getPaymentInvoiceNum());
        payment.setPaymentDate(paymentRequestDao.getPaymentDate());
        return payment;
    }

    // Convert a Qualification Entity into a Qualification Response Dao
    public static QualificationResponseDao conEntityToDao(Qualification qualification){
        QualificationResponseDao qualificationResponseDao = new QualificationResponseDao();
        qualificationResponseDao.setId(qualification.getId());
        qualificationResponseDao.setQualificationType(qualification.getQualificationType());
        qualificationResponseDao.setQualificationValidDate(qualification.getQualificationValid());
        qualificationResponseDao.setEmpId(qualification.getEmployee().getId());
        return qualificationResponseDao;
    }

    // Convert Time Keeping Entity into a Time Keeping Response Dao
    public static TimeRecordResponseDao conEntityToDao(TimeRecord timeRecord){
        TimeRecordResponseDao timeRecordResponseDao = new TimeRecordResponseDao();
        timeRecordResponseDao.setId(timeRecord.getId());
        timeRecordResponseDao.setWorkHours(timeRecord.getWorkHours());
        timeRecordResponseDao.setWorkDay(timeRecord.getWorkDay());
        timeRecordResponseDao.setJob(timeRecord.getJob());
        timeRecordResponseDao.setEmpId(timeRecord.getEmployee().getId());
        return timeRecordResponseDao;
    }

    // Convert a Qualification Request into a Qualification Object
    public static Qualification conDaoToEntity(QualificationRequestDao qualificationRequestDao){
        Qualification qualification = new Qualification();
        qualification.setQualificationType(qualificationRequestDao.getQualificationType());
        qualification.setQualificationValid(LocalDate.parse(qualificationRequestDao.getQualificationValidDate().toString()));
        return qualification;
    }

    // Convert a TimeRecord Request into a Time Record Object
    public static TimeRecord conDaoToEntity(TimeRecordRequestDao timeRecordRequestDao) {
        TimeRecord timeRecord = new TimeRecord();
        timeRecord.setWorkHours(timeRecordRequestDao.getWorkHours());
        timeRecord.setWorkDay(timeRecordRequestDao.getWorkDay());
        timeRecord.setJob(timeRecordRequestDao.getJob());
        return timeRecord;
    }

    // Convert a Member Entity into a MemberResponse Dao
    public static MemberResponseDao conEntityToDao(Member member){
        MemberResponseDao md = new MemberResponseDao();
        md.setId(member.getId());
        md.setMemberName(member.getMemberName());
        md.setMemMatNumber(member.getMemberMatNumber());
        md.setMemEmail(member.getMemberEmail());
        md.setMemberContribution(member.getMemberContribution());
        md.setMemberStats(member.getMemberStatus());
        md.setMemberJoinDate(member.getMemberJoinDate());
        md.setMemberPaymentType(member.getMemberPaymentType());

        return md;

    }

    // Convert a MemberRequest Dao into a Member Entity
    public static Member conDaoToEntity(MemberRequestDao memberRequestDao){
        Member member = new Member();
        member.setMemberName(memberRequestDao.getMemberName());
        member.setMemberMatNumber(memberRequestDao.getMemMatNumber());
        member.setMemberEmail(memberRequestDao.getMemEmail());
        member.setMemberContribution(memberRequestDao.getMemberContribution());
        member.setMemberStatus(memberRequestDao.getMemberStats());
        member.setMemberJoinDate(LocalDate.now());
        member.setMemberPaymentType(memberRequestDao.getMemberPaymentType());
        return member;
    }

    // Convert an EnergySourceRequestsDao into an EnergySource Object
    public static EnergySource conDaoToEntity(EnergySourceRequestsDao energySourceRequestsDao){
        EnergySource energySource = new EnergySource();
        energySource.setEnergyCapacity(energySourceRequestsDao.getEnergyCapacity());
        energySource.setEnergyType(energySourceRequestsDao.getEnergyType());
        energySource.setStartDate(energySourceRequestsDao.getStartDate());
        return energySource;
    }

    // Convert an EnergySource Object into a EnergySourceResponse Dao
    public static EnergySourceResponseDao conEntityToDao(EnergySource energySource){
        EnergySourceResponseDao energySourceResponseDao = new EnergySourceResponseDao();
        energySourceResponseDao.setEnergySourceId(energySource.getId());
        energySourceResponseDao.setEnergyCapacity(energySource.getEnergyCapacity());
        energySourceResponseDao.setEnergyType(energySource.getEnergyType());
        energySourceResponseDao.setEnergyAvailable(energySource.getEnergyAvailable());
        energySourceResponseDao.setStartDate(energySource.getStartDate());

        // Add Alert Signals, if there are any
        List<AlertSignalResponseDao> alertSignals = new ArrayList<>();

        if (energySource.getAlertSignalList() != null){
            for (AlertSignal alertSignal : energySource.getAlertSignalList()){
                alertSignals.add(conEntityToDao(alertSignal));
            }
        }

        energySourceResponseDao.setAlertSignalResponseDaoList(alertSignals);

        // Add Daily Productions, if there are any
        List<DailyProductionResponseDao> dailyProducts = new ArrayList<>();

        if (energySource.getDailyProductionList() != null){
            for (DailyProduction dailyProduction : energySource.getDailyProductionList()){
                dailyProducts.add(conEntityToDao(dailyProduction));
            }
        }

        energySourceResponseDao.setDailyProductionResponseDaoList(dailyProducts);

        // Add Maintenance Details, if there are any
        List<MaintenanceResponseDao> maintenanceList = new ArrayList<>();

        if (energySource.getMaintenanceList() != null){
            for (Maintenance maintenance : energySource.getMaintenanceList()){
                maintenanceList.add(conEntityToDao(maintenance));
            }
        }

        energySourceResponseDao.setMaintenanceResponseDaoList(maintenanceList);

        // Add Operational Status, if there are any
        List<OperationalStatusResponseDao> operationalStatusList = new ArrayList<>();

        if (energySource.getOperationalStatusList() != null){
            for (OperationalStatus operationalStatus : energySource.getOperationalStatusList()){
                operationalStatusList.add(conEntityToDao(operationalStatus));
            }
        }

        energySourceResponseDao.setOperationalStatusResponseDaoList(operationalStatusList);

        return energySourceResponseDao;
    }

    // Convert an AlertSignalRequestDao into an AlertSignal Object
    public static AlertSignal conDaoToEntity(AlertSignalRequestDao alertSignalRequestDao){
        AlertSignal alertSignal = new AlertSignal();
        alertSignal.setCurDate(alertSignalRequestDao.getCurDate());
        alertSignal.setAlarmType(alertSignalRequestDao.getAlarmType());
        alertSignal.setAlarmStatus(alertSignalRequestDao.getAlarmStatus());
        return alertSignal;
    }

    // Convert an AlertSignal Object into a AlertSignalResponseDao
    public static AlertSignalResponseDao conEntityToDao(AlertSignal alertSignal){
        AlertSignalResponseDao alertSignalResponseDao = new AlertSignalResponseDao();
        alertSignalResponseDao.setId(alertSignal.getId());
        alertSignalResponseDao.setCurDate(alertSignal.getCurDate());
        alertSignalResponseDao.setAlarmType(alertSignal.getAlarmType());
        alertSignalResponseDao.setAlarmStatus(alertSignal.getAlarmStatus());
        alertSignalResponseDao.setEnergySourceId(alertSignal.getEnergySource().getId());
        return alertSignalResponseDao;
    }

    // Convert a DailyProductionRequset into a DailyProduction Object
    public static DailyProduction conDaoToEntity(DailyProductionRequestDao dailyProductionRequestDao){
        DailyProduction dailyProduction = new DailyProduction();
        dailyProduction.setCurDate(dailyProductionRequestDao.getCurDate());
        dailyProduction.setDailyProdAmount(dailyProductionRequestDao.getDailyProdAmount());
        return dailyProduction;
    }

    // Convert a DailyProduction Object into a DailyProductionResponse
    public static DailyProductionResponseDao conEntityToDao(DailyProduction dailyProduction){
        DailyProductionResponseDao dailyProductionResponseDao = new DailyProductionResponseDao();
        dailyProductionResponseDao.setId(dailyProduction.getId());
        dailyProductionResponseDao.setCurDate(dailyProduction.getCurDate());
        dailyProductionResponseDao.setDailyProdAmount(dailyProduction.getDailyProdAmount());
        dailyProductionResponseDao.setEnergySourceId(dailyProduction.getEnergySource().getId());
        return dailyProductionResponseDao;
    }

    // Convert a MaintenanceRequest into a Maintenance Object
    public static Maintenance conDaoToEntity(MaintenanceRequestsDao maintenanceRequestsDao){
        Maintenance maintenance = new Maintenance();
        maintenance.setMaintenanceDetails(maintenanceRequestsDao.getMaintenanceDetails());
        maintenance.setMaintenanceCost(maintenanceRequestsDao.getMaintenanceCost());
        maintenance.setMaintenanceDate(maintenanceRequestsDao.getMaintenanceDate());
        maintenance.setMaintenanceStatus(maintenanceRequestsDao.getMaintenanceStatus());
        return maintenance;
    }

    // Convert a Maintenance Object into a MaintenanceResponseDao
    public static MaintenanceResponseDao conEntityToDao(Maintenance maintenance){
        MaintenanceResponseDao maintenanceResponseDao = new MaintenanceResponseDao();
        maintenanceResponseDao.setId(maintenance.getId());
        maintenanceResponseDao.setMaintenanceDetails(maintenance.getMaintenanceDetails());
        maintenanceResponseDao.setMaintenanceCost(maintenance.getMaintenanceCost());
        maintenanceResponseDao.setMaintenanceDate(maintenance.getMaintenanceDate());
        maintenanceResponseDao.setMaintenanceStatus(maintenance.getMaintenanceStatus());
        maintenanceResponseDao.setEnergySourceId(maintenance.getEnergySource().getId());
        return maintenanceResponseDao;
    }

    // Convert a OperationalStatusRequest into a OperationalStatus Object
    public static OperationalStatus conDaoToEntity(OperationalStatusRequestsDao operationalStatusRequestsDao){
        OperationalStatus operationalStatus = new OperationalStatus();
        operationalStatus.setCurDate(operationalStatusRequestsDao.getCurDate());
        operationalStatus.setOpStatus(operationalStatusRequestsDao.getOpStatus());
        return operationalStatus;
    }

    // Convert a OperationalStatus Object into a OperationalStatus Response
    public static OperationalStatusResponseDao conEntityToDao(OperationalStatus operationalStatus){
        OperationalStatusResponseDao operationalStatusResponseDao = new OperationalStatusResponseDao();
        operationalStatusResponseDao.setId(operationalStatus.getId());
        operationalStatusResponseDao.setCurDate(operationalStatus.getCurDate());
        operationalStatusResponseDao.setOpStatus(operationalStatus.getOpStatus());
        operationalStatusResponseDao.setEnergySourceId(operationalStatus.getEnergySource().getId());
        return operationalStatusResponseDao;
    }

    // Convert a Location Object into a LocationResponse
    public static LocationResponseDao conEntityToDao(Location location){
        LocationResponseDao ld = new LocationResponseDao();
        ld.setId(location.getId());
        ld.setLocName(location.getLocName());
        ld.setLocAddress(location.getLocAddress());
        ld.setLocLatitude(location.getLocLatitude());
        ld.setLocLongitude(location.getLocLongitude());
        ld.setLocStatus(location.getLocStatus());
        ld.setLocCapacity(location.getLocCapacity());
        ld.setStartDate(location.getLocStartDate());
        return ld;
    }

    // Convert a LocationRequest into a Location Object
    public static Location conDaoToEntity(LocationRequestDao locationRequestDao){
        Location location = new Location();
        location.setLocName(locationRequestDao.getLocName());
        location.setLocAddress(locationRequestDao.getLocAddress());
        location.setLocLatitude(locationRequestDao.getLocLatitude());
        location.setLocLongitude(locationRequestDao.getLocLongitude());
        location.setLocStatus(locationRequestDao.getLocStatus());
        location.setLocCapacity(locationRequestDao.getLocCapacity());
        location.setLocStartDate(locationRequestDao.getStartDate());
        return location;
    }




}
