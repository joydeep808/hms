package com.hms.hms.service;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms.hms.dto.UpdateAppointmentDto;
import com.hms.hms.entity.Appointment;
import com.hms.hms.entity.Appointment.AppointmentStatus;
import com.hms.hms.entity.Patient;
import com.hms.hms.exception.thrown_exception.NotFoundException;
import com.hms.hms.mapper.AppointmentDoctorMapper;
import com.hms.hms.mapper.AppointmentWithUserMapper;
import com.hms.hms.repository.AppointmentRepo;
import com.hms.hms.repository.PatientRepo;
import com.hms.hms.util.DateUtil;
import com.hms.hms.util.Response;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AppointmentService  {
  private final AppointmentRepo appointmentRepo;
  private final PatientRepo patientRepo;

  // @Override
  public ResponseEntity<Response<Appointment>> createAppointment(Appointment appointment) throws Exception{
   Appointment savedAppointment =  appointmentRepo.save(appointment);
  //  i have to send a recept to the user that you have successfully booked an appointment
    return Response.sendSuccessResponse("Appointment successfully created",savedAppointment );
  }

  // @Override
  public ResponseEntity<Response<AppointmentWithUserMapper>> getAppointmentById(Long appointmentId) throws Exception {
    AppointmentWithUserMapper foundAppointmentWithUserMapper = appointmentRepo.getAppointmentByIdWithUser(appointmentId).orElse(null);;    
    if (foundAppointmentWithUserMapper == null) {
      throw new NotFoundException("Appointment not found");
    }
    return Response.sendSuccessResponse("Appointment found successfully done!", foundAppointmentWithUserMapper);
  }

  // @Override
  public ResponseEntity<Response<Page<Appointment>>> getAllAppointments(Integer page) throws Exception {
    Pageable pageable= PageRequest.of(page<=0 ? 0 :page-1, 10);
   long startOfTodayInMillis = DateUtil.getStartOfTodayInMillis();
   long endOfTodayInMillis = DateUtil.getEndOfTodayInMillis();
   Page<Appointment> todayAppointment = appointmentRepo.findTodayAppointment(startOfTodayInMillis , endOfTodayInMillis , pageable);;
   if (todayAppointment == null || todayAppointment.isEmpty()) {
    throw new NotFoundException( "There is no appointment for today");
   }
   return Response.sendSuccessResponse("Successfully found", todayAppointment);
  }

  // @Override
  public ResponseEntity<Response<Appointment>> updateAppointment(UpdateAppointmentDto updatedAppointment)
      throws Exception {
        Appointment foundAppointment = appointmentRepo.findById(updatedAppointment.getAppointmentId()).orElse(null);
        if (foundAppointment == null) {
          throw new NotFoundException("Appointment not found to update");
        }
        updatedAppointment.getDateTime().ifPresent(foundAppointment::setDateTime);
        updatedAppointment.getDoctor().ifPresent(foundAppointment::setDoctor);
        updatedAppointment.getPatient().ifPresent(foundAppointment::setPatient);
        // updatedAppointment.getStatus().ifPresent(foundAppointment.setStatus(AppointmentStatus.valueOf(updatedAppointment.getStatus().get())));
        updatedAppointment.getStatus().ifPresent((p)-> foundAppointment.setStatus(AppointmentStatus.valueOf(p)));
        updatedAppointment.getProblemDescription().ifPresent(foundAppointment::setProblemDescription);
        Appointment appointmentUpdated = appointmentRepo.save(foundAppointment);
        return Response.sendSuccessResponse("Appointment update successfully done", appointmentUpdated);
  }

  // @Override
  public ResponseEntity<Response<String>> deleteAppointment(Long appointmentId) throws Exception {
    Appointment foundAppointment = appointmentRepo.findById(appointmentId).orElse(null);
    if (foundAppointment == null) {
        throw new NotFoundException("Appointment not found");
    }
    appointmentRepo.deleteById(foundAppointment.getAppointmentId());
    return Response.sendSuccessResponse("Appointment delete successfully done!", "");
  }

  // @Override
  public ResponseEntity<Response<Page<Appointment>>> getAppointmentsByPatientId(String email , Integer page) throws Exception {
    Patient foundPatient = patientRepo.findByEmail(email).orElse(null);
    if (foundPatient == null) {
      throw new NotFoundException("Patient not found with this email");
    }
    Pageable pageable = PageRequest.of(page<=0 ? 0 :page-1, 10  , Sort.by("date_time").descending());
    Page<Appointment> userAppointments = appointmentRepo.findUserAppointments(foundPatient.getPatientId() , pageable);;
    if (userAppointments == null || userAppointments.isEmpty()) {
      throw new NotFoundException("User dont have any appointments");
    }
    return Response.sendSuccessResponse("Appointments found with the user", userAppointments);

  }

  // @Override

  // @Override
  public ResponseEntity<Response<Page<AppointmentDoctorMapper>>> getAppointmentsByStatus(String start , String end , String status , Integer page) throws Exception {
    Pageable pageable = PageRequest.of(page <= 0 ? 0 : page-1, 15);
    long startDate = DateUtil.convertDateToMillis(start);
    long endDate = DateUtil.convertDateToMillis(end);;
    Page<AppointmentDoctorMapper> appointmentsByStatus = appointmentRepo.getAppointmentsByStatus(pageable , status ,startDate , endDate);;
    if (appointmentsByStatus == null || appointmentsByStatus.isEmpty()) {
      throw new NotFoundException("Appointments are not found ");
    }
    return Response.sendSuccessResponse("Successsfully found", appointmentsByStatus);
  }

  // @Override
  public ResponseEntity<Response<Page<AppointmentDoctorMapper>>> getAppointmentsByDateTimeRange(String startDate, Long endDate , Integer page) throws Exception {
    long endOfTodayInMillis = DateUtil.getEndOfTodayInMillis();
    long startOfTodayInMillis = DateUtil.convertDateToMillis(startDate);
    Pageable pageable = PageRequest.of(page <= 0 ? 0 : page -1, 10);
    Page<AppointmentDoctorMapper> appointmentsByRange = appointmentRepo.getAppointmentsByRange(pageable, startOfTodayInMillis, endOfTodayInMillis);;
    if (appointmentsByRange ==null || appointmentsByRange.isEmpty() ) {
      throw new NotFoundException("No Appointments are found within this period");
    }
    return Response.sendSuccessResponse("Appointments found successfully done!", appointmentsByRange);
  }

  
}
