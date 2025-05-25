package com.sp.hsbiegapi.services.serviceimpl.emploServiceImpls;

import com.sp.hsbiegapi.daos.RequestDaos.emploRequestDaos.QualificationRequestDao;
import com.sp.hsbiegapi.daos.ResponseDaos.emploResponseDaos.QualificationResponseDao;
import com.sp.hsbiegapi.models.emploModels.Employee;
import com.sp.hsbiegapi.models.emploModels.Qualification;
import com.sp.hsbiegapi.repositories.emploRepositories.EmployeeRepository;
import com.sp.hsbiegapi.repositories.emploRepositories.QualificationRepository;
import com.sp.hsbiegapi.services.emploServices.QualificationService;
import com.sp.hsbiegapi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QualificationServiceImpl implements QualificationService {

    private final QualificationRepository qualificationRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public QualificationServiceImpl(QualificationRepository qualificationRepository, EmployeeRepository employeeRepository) {
        this.qualificationRepository = qualificationRepository;
        this.employeeRepository = employeeRepository;
    }

    //--------------------  Qualification Model based Methods   --------------------//

    @Override
    public List<QualificationResponseDao> getAllEmployeeQualifications(long employeeId) {
        try {

            // Get all the Qualifications of an Employee based on the passed in EmployeeId
            List<Qualification> qualificationList = qualificationRepository.findAllByEmployeeId(employeeId);

            List<QualificationResponseDao> qualificationResponseDaoList = new ArrayList<>();

            // Check for Qualifications and assign them to Response Object
            if (!qualificationList.isEmpty()){
                for (Qualification qualification:qualificationList){
                    qualificationResponseDaoList.add(Mapper.conEntityToDao(qualification));
                }
            }

            return qualificationResponseDaoList;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public QualificationResponseDao getSingleQualification(long qualificationId) {
        try {

            // Get a single Qualification based on the passed id
            Optional<Qualification> qualification = qualificationRepository.findById(qualificationId);

            // Check if this Qualification exists or not
            if (qualification.isPresent()){
                return Mapper.conEntityToDao(qualification.get());
            }

            System.out.println("This Qualification does not exists in the Database");
            return new QualificationResponseDao();

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void addQualificationToEmployee(long employeeId, QualificationRequestDao qualificationRequestDao) {
        try {

            // Get the Employee first to check if the Employee exists in the Database
            Optional<Employee> employee = employeeRepository.findById(employeeId);

            // Check if the Employee exists, because without an existing Employee,it is not possible to add a Qualification.
            if (employee.isPresent()){

                // Check all the Required fields have been entered by the User
                if (qualificationRequestDao.getQualificationType() != null && qualificationRequestDao.getQualificationValidDate() != null){

                    // Convert Dao to an Entity
                    Qualification qualification = Mapper.conDaoToEntity(qualificationRequestDao);
                    qualification.setEmployee(employee.get());

                    // Save the Qualification into the Database
                    qualificationRepository.save(qualification);
                    System.out.println("Qualification added successfully");

                }else{
                    System.out.println("Required fields does not have Information");
                }

            }else {
                System.out.println("Employee does not exists in the Database");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateEmployeeQualification(long employeeId, QualificationRequestDao qualificationRequestDao) {

    }

    @Override
    public void deleteEmployeeQualification(long qualificationId) {

        try {

            // Get and Check if this Qualification exists in the Database
            Optional<Qualification> qualification = qualificationRepository.findById(qualificationId);

            if (qualification.isPresent()){
                qualificationRepository.delete(qualification.get());
                System.out.println("Qualification has been deleted");
            }

            System.out.println("This Qualification does not exists in the Database");

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
