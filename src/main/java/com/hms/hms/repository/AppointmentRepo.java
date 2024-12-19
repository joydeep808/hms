package com.hms.hms.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hms.hms.entity.Appointment;
import com.hms.hms.mapper.AppointmentDoctorMapper;
import com.hms.hms.mapper.AppointmentWithUserMapper;


@Repository
public interface AppointmentRepo extends JpaRepository<Appointment , Long>{

  @Query(value = "SELECT a.* , p.name , p.dob , p.contact , p.email, p.blood_group , p.allergies , p.patient_status , p.category FROM appointment as a LEFT JOIN patient as p ON p.patient_id = a.patient WHERE a.appointment_id = :id" , nativeQuery = true)
  Optional<AppointmentWithUserMapper> getAppointmentByIdWithUser(@Param("id") Long id);

  @Query(value = "SELECT a FROM appointment a WHERE a.date_time >= : start AND a.date_time <= :end")
  Page<Appointment> findTodayAppointment(@Param("start") Long start , @Param("end") Long endOfDay , Pageable pageable);

  @Query(value = "SELECT * from appointment where patient = :id" , nativeQuery = true)
  Page<Appointment> findUserAppointments(@Param("id") Long id , Pageable page);

  
  @Query(value = "SELECT a.* , p.name FROM appointment AS a LEFT JOIN patient as p ON p.patient_id = a.patient WHERE status = :status AND a.date_time >= : start AND a.date_time <= :end")
  Page<AppointmentDoctorMapper> getAppointmentsByStatus(Pageable pageable , @Param("status") String status , @Param("start") Long start , @Param("end") Long end) ;
  
  
  @Query(value = "SELECT a.* , p.name FROM appointment AS a LEFT JOIN patient as p ON p.patient_id = a.patient WHERE  a.date_time >= : start AND a.date_time <= :end")
  Page<AppointmentDoctorMapper> getAppointmentsByRange(Pageable pageable , @Param("start") Long start , @Param("end") Long end) ;
  
  @Query(value = "SELECT a.* , p.name FROM appointment AS a LEFT JOIN patient as p ON p.patient_id = a.patient WHERE doctor = :id AND a.date_time >= : start AND a.date_time <= :end" ,nativeQuery = true)
  Page<AppointmentDoctorMapper> findDoctorAppointmentsWithDateRange(@Param("id") Long id , @Param("start") Long start , @Param("end") Long end , Pageable pageable);


  @Query(value = "SELECT a.* , p.name FROM appointment AS a LEFT JOIN patient as p ON p.patient_id = a.patient WHERE doctor = :doctor " ,nativeQuery = true)
  Page<AppointmentDoctorMapper> getAppointmentsByDoctorId(Pageable pageable , @Param("doctor") Long doctor);
}