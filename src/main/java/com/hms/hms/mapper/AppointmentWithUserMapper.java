package com.hms.hms.mapper;

public interface AppointmentWithUserMapper {
  Long getAppointmentId();
  Long getPatient();
  Long getDoctor();
  Long getDateTime();
  String getStatus(); 
  String getProblemDescription();
  String getName();
  Long getDob();
  Long getContact();
  String getEmail();
  String getBloodGroup();
  String getAllergies();
  String getPatientStatus();
  String getCategory();
}
