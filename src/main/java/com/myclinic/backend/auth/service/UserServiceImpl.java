package com.myclinic.backend.auth.service;

import com.myclinic.backend.auth.entity.UserAuth;
import com.myclinic.backend.auth.jwt.JwtUtil;
import com.myclinic.backend.auth.model.response.LoginResponse;
import com.myclinic.backend.auth.service.repoHandler.UserAuthRepoHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.myclinic.backend.constants.AdminConstants.INVALID_CREDENTIALS;
import static com.myclinic.backend.constants.AdminConstants.LOGIN_FAILED;

@Service
public class UserServiceImpl implements UserService {

    private final UserAuthRepoHandler userAuthRepoHandler;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserAuthRepoHandler userAuthRepoHandler, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.userAuthRepoHandler = userAuthRepoHandler;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public LoginResponse login(String username, String password) {
        UserAuth user = userAuthRepoHandler.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(INVALID_CREDENTIALS));
        try{
            if (bCryptPasswordEncoder.matches(password,user.getPassword())){
                Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(String.valueOf(user.getUserId()), password));
                if (authenticate.isAuthenticated()){
                    String token = jwtUtil.generateToken(user);
                    return new LoginResponse(token,user.getRole());
                }else {
                    throw new BadCredentialsException(INVALID_CREDENTIALS);
                }
            }
        }catch (Exception e){
            throw new RuntimeException(LOGIN_FAILED);
        }
        return null;
    }
}
