package com.myclinic.backend.auth.service;

import com.myclinic.backend.auth.entity.UserAuth;
import com.myclinic.backend.auth.jwt.JwtUtil;
import com.myclinic.backend.auth.model.response.LoginResponse;
import com.myclinic.backend.auth.service.repoHandler.UserAuthRepoHandler;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.myclinic.backend.auth.constants.AuthConstants.INVALID_CREDENTIALS;
import static com.myclinic.backend.auth.constants.AuthConstants.LOGIN_SUCCESSFUL;

@Service
public class UserServiceImpl implements UserService {

    private final UserAuthRepoHandler userAuthRepoHandler;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserAuthRepoHandler userAuthRepoHandler, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil) {
        this.userAuthRepoHandler = userAuthRepoHandler;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public LoginResponse login(String username, String password) {
        UserAuth user = userAuthRepoHandler.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(INVALID_CREDENTIALS));

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException(INVALID_CREDENTIALS);
        }

        String token = jwtUtil.generateToken(user);

        return new LoginResponse(LOGIN_SUCCESSFUL, token, user.getRole());
    }
}
