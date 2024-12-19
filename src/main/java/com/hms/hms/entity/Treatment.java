package com.hms.hms.entity;

import lombok.*;

import java.time.ZonedDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Data
@AllArgsConstructor
@Builder
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long treatmentId;


    @NotNull(message = "Appointment id is required" )
    private Long appointment;

    @NotBlank(message = "Description is required")
    @Size(max = 1000)
    private String description;

    private Long startDate;
    private Long endDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TreatmentStatus status;

    private Long createdAt;
    public enum TreatmentStatus {
        ONGOING, COMPLETED, CANCELLED
    }
    public Treatment(){
        this.createdAt = ZonedDateTime.now().toInstant().toEpochMilli();
    }

}
