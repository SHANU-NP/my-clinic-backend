package com.myclinic.backend.patient.service.rephandler;

import com.myclinic.backend.patient.entity.PatientMedication;
import com.myclinic.backend.patient.repository.PatientMedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PatientMedicationRepoHandler {
    private final PatientMedicationRepository repository;

    public PatientMedicationRepoHandler(PatientMedicationRepository repository) {
        this.repository = repository;
    }

    public PatientMedication save(PatientMedication medication) {
        return repository.save(medication);
    }

    public List<PatientMedication> findByClinicAndPatient(String clinicId, Long patientId) {
        return repository.findAllByClinicIdAndPatientId(clinicId, patientId);
    }

    public List<PatientMedication> findByConsultationId(UUID consultationId) {
        return repository.findAllByConsultationId(consultationId);
    }
}
