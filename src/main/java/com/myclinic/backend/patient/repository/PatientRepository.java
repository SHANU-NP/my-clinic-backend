package com.myclinic.backend.patient.repository;

import com.myclinic.backend.patient.entity.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientDetails,Long> {
}
