package com.myclinic.backend.pharmacy.service;

import com.myclinic.backend.auth.model.request.LoginReq;
import com.myclinic.backend.auth.model.response.LoginResponse;
import com.myclinic.backend.auth.service.UserServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PharmacyService {

    private final UserServiceImpl userService;

    public PharmacyService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public LoginResponse login(LoginReq loginReq) {
        return userService.login(loginReq.getUsername(),loginReq.getPassword());

    }
}
