package com.hms.hms.entity;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.ZonedDateTime;

@Entity
@Data
@AllArgsConstructor
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long patientId;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100)
    private String name;

    @NotNull(message = "Date of birth is required")
    private String dob;


    private Long contact;

    @Email(message = "Please provide a valid email address")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Blood group is required")
    private String bloodGroup;

    private String allergies;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PatientStatus patientStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PatientCategory category;

    public Long createdAt;
    public enum PatientStatus {
        ACTIVE, INACTIVE, DECEASED
    }

    public enum PatientCategory {
        OPD, IPD, EMERGENCY
    }

    public Patient(){
        this.patientStatus = PatientStatus.INACTIVE;
        this.createdAt = ZonedDateTime.now().toInstant().toEpochMilli();
    }
    
}