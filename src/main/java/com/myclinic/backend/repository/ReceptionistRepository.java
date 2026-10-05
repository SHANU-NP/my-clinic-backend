package com.myclinic.backend.repository;

import com.myclinic.backend.entity.ReceptionistDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceptionistRepository extends JpaRepository<ReceptionistDetails, Long> {


}
