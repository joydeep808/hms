package com.hms.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.hms.entity.Prescription;


@Repository
public interface PrescriptionRepo extends JpaRepository<Prescription , Long>{

  
}