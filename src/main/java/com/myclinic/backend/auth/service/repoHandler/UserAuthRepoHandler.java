package com.myclinic.backend.auth.service.repoHandler;

import com.myclinic.backend.auth.entity.UserAuth;
import com.myclinic.backend.auth.repo.UserAuthRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthRepoHandler {

    private final UserAuthRepository userAuthRepository;

    public UserAuthRepoHandler(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }

    public Optional<UserAuth> findByUserName(String username) {
      return userAuthRepository.findByUsername(username);
    }
}
