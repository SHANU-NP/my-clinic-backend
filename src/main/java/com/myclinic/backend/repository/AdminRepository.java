package com.myclinic.backend.repository;

import com.myclinic.backend.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Clinic, Long> {
}
