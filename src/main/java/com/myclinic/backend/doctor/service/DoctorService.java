package com.myclinic.backend.doctor.service;

import com.myclinic.backend.auth.model.response.LoginResponse;
import com.myclinic.backend.auth.service.UserServiceImpl;
import com.myclinic.backend.doctor.model.request.LoginReq;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final UserServiceImpl userService;

    public DoctorService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public LoginResponse login(LoginReq loginReq) {
        return userService.login(loginReq.getUsername(), loginReq.getPassword());
    }
}
