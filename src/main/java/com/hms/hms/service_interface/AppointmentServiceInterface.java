package com.hms.hms.service_interface;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.hms.hms.dto.UpdateAppointmentDto;
import com.hms.hms.entity.Appointment;
import com.hms.hms.mapper.AppointmentWithUserMapper;
import com.hms.hms.util.Response;

public interface AppointmentServiceInterface {

  public ResponseEntity<Response<Appointment>> createAppointment(Appointment appointment) throws Exception;

  public ResponseEntity<Response<AppointmentWithUserMapper>>  getAppointmentById(Long appointmentId) throws Exception;

  public ResponseEntity<Response<Page<Appointment>>> getAllAppointments(Integer page) throws Exception;

  public ResponseEntity<Response<Appointment>> updateAppointment(UpdateAppointmentDto update) throws Exception;

  public ResponseEntity<Response<Appointment>> deleteAppointment(Long appointmentId) throws Exception;

  public ResponseEntity<Response<Appointment>> getAppointmentsByPatientId(Long patientId) throws Exception;

  public ResponseEntity<Response<Appointment>> getAppointmentsByDoctorId(Long doctorId) throws Exception;

  public ResponseEntity<Response<Appointment>> getAppointmentsByStatus(Appointment.AppointmentStatus status) throws Exception;

  public ResponseEntity<Response<Appointment>> getAppointmentsByDateTimeRange(Long startDate, Long endDate) throws Exception;

  // Fetch appointments for today
  public ResponseEntity<Response<Appointment>> getTodaysAppointments(Long doctorId) throws Exception;

  // Fetch appointments for a specific doctor in a given date range
  public ResponseEntity<Response<Appointment>> getAppointmentsByDoctorAndDateRange(Long doctorId, Long startDate, Long endDate) throws Exception;
}
