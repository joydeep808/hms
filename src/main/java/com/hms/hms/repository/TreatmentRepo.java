package com.hms.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hms.hms.entity.Treatment;
import com.hms.hms.mapper.TreatmentWithFullInfo;

@Repository
public interface TreatmentRepo extends JpaRepository<Treatment , Long>{

  @Query(value = "SELECT * from treatment where appoitment = :appoitment " , nativeQuery = true)
  Optional<Treatment> isTreatmentExist(@Param("appoitment") Long appoitment);
  

  @Query(value = "SELECT t.treatment_id , t.status , t.description , t.start_date , t.end_date , t.created_at , a.date_time , a.problem_description ,  p.name , p.contact , p.email , p.dob , p.blood_group , p.allergies , p.category from treatment as t left join appointment as a on a.appointment_id = t.appoitment left join patient as p on p.patient_id = a.patient where t.treatment_id = :treatmentId" , nativeQuery = true)
  Optional<TreatmentWithFullInfo> findTreatmentInfo(Long treatmentId);
}