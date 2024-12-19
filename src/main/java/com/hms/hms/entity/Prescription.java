package com.hms.hms.entity;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;

    @NotNull
    private Long treatment;

    @NotBlank(message = "Medication name is required")
    private String medicationName;

    @NotBlank(message = "Dosage is required")
    private String dosage;

    @NotBlank(message = "Instructions are required")
    private String instructions;
}