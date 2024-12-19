package com.hms.hms.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @NotNull(message = "Patient id is required")
    private Long patient;

    @NotBlank(message = "Diagnosis is required")
    private String diagnosis;

    private String treatmentHistory;

    @NotNull(message = "Record date is required")
    private Long recordDate;

    private String prescriptionHistory;
}