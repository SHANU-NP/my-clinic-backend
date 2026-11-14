package com.myclinic.backend.repository;

import com.myclinic.backend.entity.PatientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientDetails,Long> {
}
