package com.myclinic.backend.auth.service.repoHandler;

import com.myclinic.backend.auth.entity.UserAuth;
import com.myclinic.backend.auth.repo.UserAuthRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserAuthRepoHandler {

    private final UserAuthRepository userAuthRepository;

    public UserAuthRepoHandler(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    public Optional<UserAuth> findByUserName(String username) {
        return userAuthRepository.findByUsername(username);
    }

    public Optional<UserAuth> findByUserId(UUID userId) {
        return userAuthRepository.findById(userId);
    }

    public boolean existsByUsername(String username) {
        return userAuthRepository.findByUsername(username).isPresent();
    }

    public UserAuth save(UserAuth userAuth) {
        return userAuthRepository.save(userAuth);
    }

    public List<UserAuth> findAllUserByTenantId(String clinicId) {
        return userAuthRepository.findAllByTenantId(clinicId);
    }
}
