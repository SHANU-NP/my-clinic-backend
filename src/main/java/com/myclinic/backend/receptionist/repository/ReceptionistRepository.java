package com.myclinic.backend.receptionist.repository;

import com.myclinic.backend.receptionist.entity.ReceptionistDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReceptionistRepository extends JpaRepository<ReceptionistDetails, UUID> {
    boolean existsByIdAndClinicId(UUID id, String clinicId);
}
