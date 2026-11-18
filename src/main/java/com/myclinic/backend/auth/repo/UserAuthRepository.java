package com.myclinic.backend.auth.repo;

import com.myclinic.backend.auth.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, UUID> {
    Optional<UserAuth> findByUsername(String username);

    List<UserAuth> findAllByTenantId(String clinicId);
}
