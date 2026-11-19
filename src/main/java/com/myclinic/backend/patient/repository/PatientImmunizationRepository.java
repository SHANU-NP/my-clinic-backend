package com.myclinic.backend.patient.repository;

import com.myclinic.backend.patient.entity.PatientImmunization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PatientImmunizationRepository extends JpaRepository<PatientImmunization, Long> {
    List<PatientImmunization> findAllByClinicIdAndPatientId(String clinicId, Long patientId);
    List<PatientImmunization> findAllByConsultationId(UUID consultationId);
}
