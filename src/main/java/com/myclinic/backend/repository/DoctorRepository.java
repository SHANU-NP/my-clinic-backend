package com.myclinic.backend.repository;

import com.myclinic.backend.entity.DoctorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorDetails,Long> {
}
