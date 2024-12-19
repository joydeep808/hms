package com.hms.hms.service_interface;

import org.springframework.http.ResponseEntity;

import com.hms.hms.entity.Patient;
import com.hms.hms.entity.Patient.PatientCategory;
import com.hms.hms.entity.Patient.PatientStatus;
import com.hms.hms.util.Response;

public interface PatientServiceInterface {
  public <T> ResponseEntity<Response<T>> createPatient(Patient patient);

  public <T> ResponseEntity<Response<T>> getPatientById(Long patientId);

  public <T> ResponseEntity<Response<T>> getAllPatients();

  public <T> ResponseEntity<Response<T>> updatePatient(Long patientId, Patient updatedPatient);

  public <T> ResponseEntity<Response<T>> deletePatient(Long patientId);

  public <T> ResponseEntity<Response<T>> getPatientsByCategory(PatientCategory category);

  public <T> ResponseEntity<Response<T>> getPatientsByStatus(PatientStatus status);

}
