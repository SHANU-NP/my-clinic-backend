package com.myclinic.backend.patient.repository;

import com.myclinic.backend.patient.entity.PatientAllergy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PatientAllergyRepository extends JpaRepository<PatientAllergy, Long> {
    List<PatientAllergy> findAllByClinicIdAndPatientId(String clinicId, Long patientId);
    List<PatientAllergy> findAllByConsultationId(UUID consultationId);
}
