package com.hms.hms.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;

    @NotBlank(message = "Department name is required")
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank(message = "Location is required")
    private String location;
    
    private Long headDoctor;
}