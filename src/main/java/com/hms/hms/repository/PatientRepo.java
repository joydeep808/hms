package com.hms.hms.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hms.hms.entity.Patient;
import com.hms.hms.entity.Patient.PatientCategory;

@Repository
public interface PatientRepo extends JpaRepository<Patient ,Long>{


  @Query(value = "SELECT * from patient where email = :email" , nativeQuery = true)
  Optional<Patient> findByEmail(@Param("email") String email );

  @Query(value = "SELECT * from patient where contact = :contact" , nativeQuery = true)
  Optional<Patient> findByContact(@Param("contact") Long contact);

  @Query(value = "SELECT * from patient where email = :email AND contact = :contact" , nativeQuery = true)
  Optional<Patient> findByEmailAndContact(String email , Long contact);


  @Query(value = "SELECT * from patient where category = :category" , nativeQuery = true)
  Page<Patient> findByCategory(Pageable pageable, String valueOf);

  @Query(value = "SELECT * from patient where patient_status = :status" , nativeQuery = true)
  Page<Patient> findByPatientStatus(Pageable pageable, String status);

  
}