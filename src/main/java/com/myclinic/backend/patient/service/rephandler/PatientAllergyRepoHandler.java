package com.myclinic.backend.patient.service.rephandler;

import com.myclinic.backend.patient.entity.PatientAllergy;
import com.myclinic.backend.patient.repository.PatientAllergyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientAllergyRepoHandler {
    private final PatientAllergyRepository repository;

    public PatientAllergyRepoHandler(PatientAllergyRepository repository) {
        this.repository = repository;
    }

    public PatientAllergy save(PatientAllergy allergy) {
        return repository.save(allergy);
    }

    public List<PatientAllergy> findByClinicAndPatient(String clinicId, Long patientId) {
        return repository.findAllByClinicIdAndPatientId(clinicId, patientId);
    }

    public List<PatientAllergy> findByConsultationId(UUID consultationId) {
        return repository.findAllByConsultationId(consultationId);
    }
}
