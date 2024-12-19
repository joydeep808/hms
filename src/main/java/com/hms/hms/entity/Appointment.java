package com.hms.hms.entity;


import lombok.*;

import java.time.ZonedDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Data

@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @NotNull(message = "Patient id is required")
    private Long patient;
    @NotNull(message = "Doctor id is required")
    private Long doctor;

    @NotNull(message = "Appointment date and time is required")
    private Long dateTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Size(max = 500)
    private String problemDescription;

    public enum AppointmentStatus {
        SCHEDULED, CONFIRMED, CANCELLED, COMPLETED
    }
    public Appointment(){
        this.dateTime = ZonedDateTime.now().toInstant().toEpochMilli();
        this.status = AppointmentStatus.CONFIRMED;
    }

}