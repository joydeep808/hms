package com.hms.hms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorId;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100)

    private String name;

    @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits")
    private String contact;

    @NotBlank(message = "Specialization is required")
    private String specialization;

    @NotNull(message = "Department is required")
    private Long department;
}