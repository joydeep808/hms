package com.hms.hms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms.hms.entity.Appointment;
import com.hms.hms.entity.Prescription;
import com.hms.hms.entity.Treatment;
import com.hms.hms.exception.thrown_exception.AlreadyExistException;
import com.hms.hms.exception.thrown_exception.GenericException;
import com.hms.hms.exception.thrown_exception.NotFoundException;
import com.hms.hms.mapper.TreatmentWithFullInfo;
import com.hms.hms.repository.AppointmentRepo;
import com.hms.hms.repository.PrescriptionRepo;
import com.hms.hms.repository.TreatmentRepo;
import com.hms.hms.util.Response;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TreatmentService {
  private final TreatmentRepo treatmentRepo;
  private final AppointmentRepo appointmentRepo;
  private final PrescriptionRepo prescriptionRepo;

  public ResponseEntity<Response<String>> createTreatment(Treatment treatment) throws Exception {
    Treatment foundTreatment = treatmentRepo.isTreatmentExist(treatment.getAppointment()).orElse(null);
    if (foundTreatment != null) {
      throw new AlreadyExistException(
          "Treatment already running for this appointment startDate" + treatment.getStartDate());
    }
    Appointment foundAppointment = appointmentRepo.findById(treatment.getAppointment()).orElse(null);
    ;
    if (foundAppointment.getStatus() == Appointment.AppointmentStatus.COMPLETED) {
      throw new AlreadyExistException("Appointment is already completed");
    }
    if (foundAppointment.getStatus() == Appointment.AppointmentStatus.CANCELLED) {
      throw new AlreadyExistException("Appointment is already cancelled");
    }
    treatmentRepo.save(treatment);
    return Response.sendSuccessResponse("Treatment saved successfully done");

  }

  public ResponseEntity<Response<TreatmentWithFullInfo>> getTreatmentFullInfo(Long TreatmentId) throws Exception {
    TreatmentWithFullInfo foundTreatment = treatmentRepo.findTreatmentInfo(TreatmentId).orElse(null);
    ;
    if (foundTreatment == null) {
      throw new NotFoundException("Treatment not found");
    }
    return Response.sendSuccessResponse("Treatment found successfully done", foundTreatment);
  }

  public ResponseEntity<Response<String>> deleteTreatment(Long treatmentId) throws Exception {
    Treatment foundTreatment = treatmentRepo.findById(treatmentId).orElse(null);
    if (foundTreatment == null) {
      throw new NotFoundException("Treatment not found");
    }
    treatmentRepo.delete(foundTreatment);
    return Response.sendSuccessResponse("Treatment deleted successfully done", "");
  }

  public ResponseEntity<Response<String>> updateTreatment(Treatment treatment) throws Exception {
    Treatment foundTreatment = treatmentRepo.findById(treatment.getTreatmentId()).orElse(null);
    if (foundTreatment == null) {
      throw new NotFoundException("Treatment not found");
    }
    treatmentRepo.save(treatment);
    return Response.sendSuccessResponse("Treatment updated successfully done", "");
  }

  public ResponseEntity<Response<String>> completeTreatment(Long treatmentId) throws Exception {
    Treatment foundTreatment = treatmentRepo.findById(treatmentId).orElse(null);
    if (foundTreatment == null) {
      throw new NotFoundException("Treatment not found");
    }
    foundTreatment.setStatus(Treatment.TreatmentStatus.COMPLETED);
    treatmentRepo.save(foundTreatment);
    return Response.sendSuccessResponse("Treatment completed successfully done", "");
  }

  public ResponseEntity<Response<String>> cancelTreatment(Long treatmentId) throws Exception {
    Treatment foundTreatment = treatmentRepo.findById(treatmentId).orElse(null);
    if (foundTreatment == null) {
      throw new NotFoundException("Treatment not found");
    }
    foundTreatment.setStatus(Treatment.TreatmentStatus.CANCELLED);
    treatmentRepo.save(foundTreatment);
    return Response.sendSuccessResponse("Treatment cancelled successfully done", "");
  }

  public ResponseEntity<Response<String>> createPescriptionForUser(Prescription prescription) throws Exception {
    Treatment foundTreatment = treatmentRepo.findById(prescription.getTreatment()).orElse(null);
    ;
    if (foundTreatment == null) {
      throw new NotFoundException("Treatment not found");
    }
    if (foundTreatment.getStatus() == Treatment.TreatmentStatus.COMPLETED) {
      throw new GenericException(400, "Treatment is already completed");
    }
    if (foundTreatment.getStatus() == Treatment.TreatmentStatus.CANCELLED) {
      throw new GenericException(400, "Treatment is already cancelled");
    }
    prescriptionRepo.save(prescription);
    return Response.sendSuccessResponse("Prescription saved successfully done");

  }



    
  }