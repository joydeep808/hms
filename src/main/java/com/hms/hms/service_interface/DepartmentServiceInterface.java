package com.hms.hms.service_interface;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.hms.hms.dto.DepartmentUpdateDto;
import com.hms.hms.entity.Department;
import com.hms.hms.util.Response;

public interface DepartmentServiceInterface {

  public  ResponseEntity<Response<Department>> createDepartment(Department department) throws Exception;


  public ResponseEntity<Response<Page<Department>>> getAllDepartments(Integer page) throws Exception;

  public ResponseEntity<Response<String>> updateDepartment(Long departmentId, DepartmentUpdateDto updatedDepartment) throws Exception;

  public ResponseEntity<Response<String>>  deleteDepartment(Long departmentId) throws Exception;

  public ResponseEntity<Response<Department>> getDepartmentsByHeadDoctorId(Long headDoctorId) throws Exception;

  // public ResponseEntity<Response<Department>> getDepartmentsByLocation(String location) throws Exception;

}
