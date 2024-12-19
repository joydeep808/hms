package com.hms.hms.dto;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentUpdateDto {
  private Optional<String> name;
  private Optional<String> location;
  private Optional<Long> headDoctor;
}
