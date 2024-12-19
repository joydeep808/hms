
package com.hms.hms.service;

import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hms.hms.dto.UpdatePatientDto;
import com.hms.hms.entity.Patient;
import com.hms.hms.entity.Patient.PatientCategory;
import com.hms.hms.entity.Patient.PatientStatus;
import com.hms.hms.exception.thrown_exception.AlreadyExistException;
import com.hms.hms.exception.thrown_exception.NotFoundException;
import com.hms.hms.repository.PatientRepo;
import com.hms.hms.util.Response;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class PatientService  {

  private final PatientRepo patientRepo;
  // @Override
  public ResponseEntity<Response<Patient>> createPatient(Patient patient) throws Exception{
    Patient foundPatient = patientRepo.findByEmailAndContact(patient.getEmail(), patient.getContact()).orElse(null);;
    if (foundPatient != null) {
      throw new AlreadyExistException("Patient already exist");
    }
    patientRepo.save(patient);
    return Response.sendSuccessResponse("Patient created successfully", patient);
  } 

  // @Override
  public ResponseEntity<Response<Patient>> getPatientById(Long patientId) throws NotFoundException {
    Patient foundPatient = patientRepo.findById(patientId).orElse(null);
    if (foundPatient == null) {
      throw new NotFoundException("Patient not found");
    }
    return Response.sendSuccessResponse("Patient found successfully", foundPatient);
  }

  // @Override
  public ResponseEntity<Response<Page<Patient>>> getAllPatients(Integer page) throws NotFoundException {
    Pageable pageable = PageRequest.of(page <= 0 ? 0 :page-1, 20 ,Sort.by("created_at").ascending());
    Page<Patient> foundPatients = patientRepo.findAll(pageable);;
    if (foundPatients == null || foundPatients.isEmpty()) {
      throw new NotFoundException("No patients found in this page");
    }
    return Response.sendSuccessResponse("Successfully found!", foundPatients);

  }

  // @Override
  public ResponseEntity<Response<Patient>> updatePatient(Long patientId, UpdatePatientDto updatedPatient) throws NotFoundException {
    Patient foundPatient = patientRepo.findById(patientId).orElse(null);
    if (foundPatient == null) {
      throw new NotFoundException("Patient not found to update");
    }
    updatedPatient.getAllergies().ifPresent(foundPatient::setAllergies);
    updatedPatient.getBloodGroup().ifPresent(foundPatient::setBloodGroup);
    updatedPatient.getContact().ifPresent(foundPatient::setContact);
    updatedPatient.getDob().ifPresent(foundPatient::setDob);
    updatedPatient.getPatientStatus().ifPresent(p-> foundPatient.setPatientStatus(PatientStatus.valueOf(p)));
    updatedPatient.getCategory().ifPresent(p-> foundPatient.setCategory(PatientCategory.valueOf(p)));
    updatedPatient.getEmail().ifPresent(foundPatient::setEmail);
    updatedPatient.getName().ifPresent(foundPatient::setName);
    Patient savedPatient = patientRepo.save(foundPatient);
    return Response.sendSuccessResponse("Patient update successfully done", savedPatient);
  }

  // @Override
  public ResponseEntity<Response<String>> deletePatient(Long patientId) throws Exception{
    Patient foundPatient = patientRepo.findById(patientId).orElse(null);
    if (foundPatient == null) {
      throw new NotFoundException("Patient not found to delete");
    }
    patientRepo.delete(foundPatient);
    return Response.sendSuccessResponse("Patient deleted successfully", null);
  }

  // @Override
  public ResponseEntity<Response<Page<Patient>>> getPatientsByCategory(String category , Integer page) throws NotFoundException {
    Pageable pageable = PageRequest.of(page <= 0 ? 0 :page-1, 15 , Sort.by("created_at").descending());
    Page<Patient> foundPatients = patientRepo.findByCategory(pageable , category);;
    if (foundPatients == null || foundPatients.isEmpty()) {
      throw new NotFoundException("No patients found with this category");
    }
    return Response.sendSuccessResponse("Successfully found!", foundPatients);
  }

  // @Override
  public ResponseEntity<Response<Page<Patient>>> getPatientsByStatus(String status) throws NotFoundException {
    Pageable pageable = PageRequest.of(0, 15 , Sort.by("created_at").descending());
    Page<Patient> foundPatients = patientRepo.findByPatientStatus(pageable , status);;
    if (foundPatients == null || foundPatients.isEmpty()) {
      throw new NotFoundException("No patients found with this status");
    }
    return Response.sendSuccessResponse("Successfully found!", foundPatients);
  }

  
}