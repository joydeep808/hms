
package com.hms.hms.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms.hms.entity.Doctor;
import com.hms.hms.exception.thrown_exception.AlreadyExistException;
import com.hms.hms.exception.thrown_exception.NotFoundException;
import com.hms.hms.mapper.AppointmentDoctorMapper;
import com.hms.hms.repository.AppointmentRepo;
import com.hms.hms.repository.DoctorRepo;
import com.hms.hms.util.DateUtil;
import com.hms.hms.util.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorService {

  private final AppointmentRepo appointmentRepo;
  private final DoctorRepo doctorRepo;

  public ResponseEntity<Response<Doctor>> createDoctor(Doctor doctor)throws Exception{
    String isDoctorAlreadyExist = doctorRepo.isDoctorAlreadyExist(doctor.getContact()).orElse(null);
    if (isDoctorAlreadyExist != null) {
      throw new AlreadyExistException("Contact already exist ");
    } 

    Doctor savedDoctor = doctorRepo.save(doctor);
    return Response.sendSuccessResponse("Doctor saved successfully done!", savedDoctor);
  }

  

  public ResponseEntity<Response<Page<AppointmentDoctorMapper>>> getAppointmentsByDoctorId(Long doctorId  , Integer page) throws Exception {
    Pageable pageable = PageRequest.of(page <= 0 ? 0 :page-1, 15);
    Page<AppointmentDoctorMapper> appointmentsByDoctorId = appointmentRepo.getAppointmentsByDoctorId(pageable ,doctorId );;
    if (appointmentsByDoctorId == null) {
      throw new NotFoundException("Doctor dont have any appointment");
    }
    return Response.sendSuccessResponse("Appointments found successfully done! ", appointmentsByDoctorId);
  }

  public ResponseEntity<Response<Page<AppointmentDoctorMapper>>> getAppointmentsByDoctorAndDateRange(Long doctorId ,  Integer page) throws Exception {
    Pageable pageable = PageRequest.of(page <= 0 ? 0 : page-1 , 10 , Sort.by("date_time").ascending());
    Long endOfTodayInMillis = DateUtil.getEndOfTodayInMillis();
    Long startOfTodayInMillis = DateUtil.getStartOfTodayInMillis();
    Page<AppointmentDoctorMapper> doctorAppointments = appointmentRepo.findDoctorAppointmentsWithDateRange(doctorId , startOfTodayInMillis , endOfTodayInMillis , pageable);;;
    if (doctorAppointments == null || doctorAppointments.isEmpty() || doctorAppointments.getTotalElements() == 0) {
      throw new NotFoundException("No Appointments found for today");
    }
    return Response.sendSuccessResponse("Doctor appointments found successfully done!", doctorAppointments);
}



  
}