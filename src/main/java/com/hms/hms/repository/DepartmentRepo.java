package com.hms.hms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hms.hms.entity.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {

  @Query(value = "SELECT name from department where name = :name returning name" , nativeQuery = true)
  Optional<String> isDepartmentExist(@Param("name") String name);

  @Query(value = "SELECT * from department where head_doctor = :doctor" , nativeQuery = true)
  Optional<Department> findByHeadDoctor(@Param("doctor") Long doctor);

  


}