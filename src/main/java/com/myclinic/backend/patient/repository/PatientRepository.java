package com.myclinic.backend.patient.repository;

import com.myclinic.backend.patient.entity.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientDetails,Long> {
    Optional<PatientDetails> findByClinicIdAndId(String clinicId, Long patientId);
}
