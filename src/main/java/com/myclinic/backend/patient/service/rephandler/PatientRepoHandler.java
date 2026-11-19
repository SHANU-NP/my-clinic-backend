package com.myclinic.backend.patient.service.rephandler;

import com.myclinic.backend.patient.entity.PatientDetails;
import com.myclinic.backend.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientRepoHandler {

    private final PatientRepository patientRepository;

    public PatientRepoHandler(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientDetails savePatientDetails(PatientDetails patient) {
      return patientRepository.save(patient);
    }

    public Optional<PatientDetails> findPatientByClinicIdAndPatientId(String clinicId, Long patientId) {
        return patientRepository.findByClinicIdAndId(clinicId, patientId);
    }
}
