package com.hms.hms.dto;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAppointmentDto {
  private Long appointmentId;
  private Optional<Long> patient;
  private Optional<Long> doctor;
  private Optional<Long> dateTime;
  private Optional<String> status;
  private Optional<String> problemDescription;
}
