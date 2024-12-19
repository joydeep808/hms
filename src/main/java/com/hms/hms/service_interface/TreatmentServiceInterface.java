package com.hms.hms.service_interface;

import org.springframework.http.ResponseEntity;

import com.hms.hms.entity.Treatment;
import com.hms.hms.util.Response;

public interface TreatmentServiceInterface {

  public <T> ResponseEntity<Response<T>> createTreatment(Treatment treatment);

  public <T> ResponseEntity<Response<T>> getTreatmentById(Long treatmentId);

  public <T> ResponseEntity<Response<T>> getAllTreatments();

  public <T> ResponseEntity<Response<T>> updateTreatment(Long treatmentId, Treatment updatedTreatment);

  public <T> ResponseEntity<Response<T>> deleteTreatment(Long treatmentId);

  public <T> ResponseEntity<Response<T>> getTreatmentsByAppointmentId(Long appointmentId);

  public <T> ResponseEntity<Response<T>> getTreatmentsByStatus(Treatment.TreatmentStatus status);

  public <T> ResponseEntity<Response<T>> getTreatmentsByStartDateRange(Long startDate, Long endDate);

  // Add treatment prescription (assumed as part of the Treatment entity)
  public <T> ResponseEntity<Response<T>> addTreatmentPrescription(Long treatmentId, String prescription);
}
