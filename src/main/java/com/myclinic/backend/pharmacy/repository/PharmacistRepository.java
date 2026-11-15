package com.myclinic.backend.pharmacy.repository;

import com.myclinic.backend.pharmacy.entity.PharmacistDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacistRepository extends JpaRepository<PharmacistDetails, Long> {
}
