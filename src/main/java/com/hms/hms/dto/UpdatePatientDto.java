package com.hms.hms.dto;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePatientDto {
  private Optional<String> name;
  private Optional<String> dob;
  private Optional<String> bloodGroup;
  private Optional<String> allergies;
  private Optional<String> email;
  private Optional<Long> contact;
  private Optional<String> category;
  private Optional<String> patientStatus;
}
