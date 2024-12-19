package com.hms.hms.mapper;

public interface TreatmentWithFullInfo {
  Long getTreatmentId();
  Long getStartDate();
  Long getEndDate();
  String getStatus();
  Long getCreatedAt();
  String getProblemDescription(); 
  Long getDateTime();
  String getName();
  String getDob();
  String getContact();
  String getEmail();
  String getBloodGroup();
  String getAllergies();
  String getPatientStatus();
  String getCategory();

}
