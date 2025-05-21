package com.sp.hsbiegapi.utils;

import com.sp.hsbiegapi.daos.RequestDaos.EmployeeRequestDao;
import com.sp.hsbiegapi.daos.RequestDaos.MemberRequestDao;
import com.sp.hsbiegapi.daos.RequestDaos.QualificationRequestDao;
import com.sp.hsbiegapi.daos.RequestDaos.TimeRecordRequests.TimeRecordRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.EmployeeResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.MemberResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.PaymentResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.QualificationResponseDao;
import com.sp.hsbiegapi.daos.ResponseDaos.TimeRecordResponses.TimeRecordResponseDao;
import com.sp.hsbiegapi.models.emploModels.Employee;
import com.sp.hsbiegapi.models.emploModels.Payment;
import com.sp.hsbiegapi.models.emploModels.Qualification;
import com.sp.hsbiegapi.models.emploModels.TimeRecord;
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

        if (employee.getQualificationList() != null){
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

    // Convert an Entity into a Response Dao
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

    // Convert a Request Dao into an Entity
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
}
