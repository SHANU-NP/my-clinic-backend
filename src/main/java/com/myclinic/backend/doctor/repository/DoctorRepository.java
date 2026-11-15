package com.myclinic.backend.doctor.repository;

import com.myclinic.backend.doctor.entity.DoctorDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorDetails,Long> {
}
