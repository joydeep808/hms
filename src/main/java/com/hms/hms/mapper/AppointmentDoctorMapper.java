package com.hms.hms.mapper;

public interface AppointmentDoctorMapper {
  Long getAppointmentId();
  Long getPatient();
  Long getDoctor();
  Long getDateTime();
  String getStatus(); 
  String getProblemDescription();
  String getName();
}
