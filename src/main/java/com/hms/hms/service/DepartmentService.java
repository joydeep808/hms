package com.hms.hms.service;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms.hms.dto.DepartmentUpdateDto;
import com.hms.hms.entity.Department;
import com.hms.hms.entity.Doctor;
import com.hms.hms.exception.thrown_exception.AlreadyExistException;
import com.hms.hms.exception.thrown_exception.NotFoundException;
import com.hms.hms.repository.DepartmentRepo;
import com.hms.hms.repository.DoctorRepo;
import com.hms.hms.util.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {
  private final DepartmentRepo departmentRepo;
  private final DoctorRepo doctorRepo;
  
  // @Override
  public  ResponseEntity<Response<Department>> createDepartment(Department department) throws Exception {

    String isDepartmentExist = departmentRepo.isDepartmentExist(department.getName()).orElse(null);
    if (isDepartmentExist != null) {
      throw new AlreadyExistException( "Department already exist");
    }

   Department savedDepartment =  departmentRepo.save(department);
    return  Response.sendSuccessResponse("Department successfully saved",savedDepartment );
  }

 

  // @Override
  public  ResponseEntity<Response<Page<Department>>> getAllDepartments(Integer page) throws Exception {

    Pageable pageable = PageRequest.of(page <= 0 ? 0 :page, 10);
    Page<Department> foundDepartments = departmentRepo.findAll(pageable);;
    if (foundDepartments == null || foundDepartments.isEmpty()) {
      throw new NotFoundException("No departments found in this page");
    }
    return Response.sendSuccessResponse("Successfully found!", foundDepartments);
  }

  // @Override
  public ResponseEntity<Response<String>> updateDepartment(Long departmentId, DepartmentUpdateDto updatedDepartment)
      throws Exception {

      Department foundDepartment = departmentRepo.findById(departmentId).orElse(null);
      if (foundDepartment == null) {
        throw new NotFoundException("No department found with this id");
      }
      updatedDepartment.getLocation().ifPresent(foundDepartment::setLocation);
      updatedDepartment.getName().ifPresent(foundDepartment::setName);
      updatedDepartment.getHeadDoctor().ifPresent(foundDepartment::setHeadDoctor);
      departmentRepo.save(foundDepartment);
      return Response.sendSuccessResponse("Update succssfully done!","" );
  }

  // @Override
  public ResponseEntity<Response<String>> deleteDepartment(Long departmentId) throws Exception {
    try {
      departmentRepo.deleteById(departmentId);
      return Response.sendSuccessResponse("Department successfully deleted", "");
    } catch (Exception e) {
      return Response.sendErrorResponse("Department successfully deleted", 400);
    }
  }

  // @Override
  public ResponseEntity<Response<Department>> getDepartmentsByHeadDoctorId(Long headDoctorId) throws Exception {
    
    Department foundDepartment = departmentRepo.findByHeadDoctor(headDoctorId).orElse(null);
    if (foundDepartment == null) {
      throw new NotFoundException("Department not found with the head doctor");
    }
    return Response.sendSuccessResponse("Department found successfully done!", foundDepartment); 
  
  }

  public ResponseEntity<Response<Page<Doctor>>> getAllDoctorsFromDepartment(Long departmentId ,Integer page )throws Exception{
    Department department  = departmentRepo.findById(departmentId).orElse(null);
    if (department == null) {
      throw new NotFoundException("Department not found ");
    }
    Pageable pageable = PageRequest.of(page <= 0 ? 0:page-1, 15);
    Page<Doctor> doctorsWithDepartmentId = doctorRepo.findDoctorsWithDepartmentId(pageable , departmentId);;
    if (doctorsWithDepartmentId == null || doctorsWithDepartmentId.isEmpty()) {
      throw new NotFoundException("Doctors not found with this department");
    }
    return Response.sendSuccessResponse("Doctors found successfully done!", doctorsWithDepartmentId);
  }

  // @Override
  // public ResponseEntity<Response<Department>> getDepartmentsByLocation(String location) throws Exception {

  // }

  
  


}