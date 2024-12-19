package com.hms.hms.repository ;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hms.hms.entity.Doctor;


@Repository
public interface DoctorRepo extends JpaRepository<Doctor , Long>{

  @Query(value = "SELECT contact from doctor WHERE contact = :contact" , nativeQuery = true)
  Optional<String> isDoctorAlreadyExist(@Param("contact") String contact);

  @Query(value = "SELECT * from doctor WHERE department = :id" , nativeQuery = true) 
  Page<Doctor> findDoctorsWithDepartmentId(Pageable pageable , @Param("id") Long id);

  
}