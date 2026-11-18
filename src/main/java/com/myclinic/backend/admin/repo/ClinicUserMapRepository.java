package com.myclinic.backend.admin.repo;

import com.myclinic.backend.admin.entity.ClinicUserMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicUserMapRepository extends JpaRepository<ClinicUserMap, Long> {
    List<ClinicUserMap> findByClinicId(String clinicId);

    List<ClinicUserMap> findByUserId(String userId);

    Optional<ClinicUserMap> findByClinicIdAndUserId(String clinicId, String userId);
}
