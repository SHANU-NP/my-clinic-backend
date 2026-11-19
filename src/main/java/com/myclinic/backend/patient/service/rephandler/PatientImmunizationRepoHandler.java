package com.myclinic.backend.patient.service.rephandler;

import com.myclinic.backend.patient.entity.PatientImmunization;
import com.myclinic.backend.patient.repository.PatientImmunizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientImmunizationRepoHandler {
    private final PatientImmunizationRepository repository;

    public PatientImmunizationRepoHandler(PatientImmunizationRepository repository) {
        this.repository = repository;
    }

    public PatientImmunization save(PatientImmunization immunization) {
        return repository.save(immunization);
    }

    public List<PatientImmunization> findByClinicAndPatient(String clinicId, Long patientId) {
        return repository.findAllByClinicIdAndPatientId(clinicId, patientId);
    }

    public List<PatientImmunization> findByConsultationId(UUID consultationId) {
        return repository.findAllByConsultationId(consultationId);
    }
}
