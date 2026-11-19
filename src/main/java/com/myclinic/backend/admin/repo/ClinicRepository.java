package com.myclinic.backend.admin.repo;

import com.myclinic.backend.admin.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, UUID> {
    List<Clinic> findByNameContainingIgnoreCase(String clinicName);
}
