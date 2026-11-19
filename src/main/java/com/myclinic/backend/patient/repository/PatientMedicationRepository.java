package com.myclinic.backend.patient.repository;

import com.myclinic.backend.patient.entity.PatientMedication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PatientMedicationRepository extends JpaRepository<PatientMedication, Long> {
    List<PatientMedication> findAllByClinicIdAndPatientId(String clinicId, Long patientId);
    List<PatientMedication> findAllByConsultationId(UUID consultationId);
}
