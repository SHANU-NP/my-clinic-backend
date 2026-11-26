package com.myclinic.backend.pharmacy.service;

import com.myclinic.backend.auth.model.request.LoginReq;
import com.myclinic.backend.auth.model.response.LoginResponse;
import com.myclinic.backend.auth.service.UserServiceImpl;
import com.myclinic.backend.doctor.model.response.DoctorResponse;
import com.myclinic.backend.doctor.service.DoctorService;
import org.springframework.stereotype.Service;

@Service
public class PharmacyService {

    private final UserServiceImpl userService;
    private final DoctorService doctorService;

    public PharmacyService(UserServiceImpl userService, DoctorService doctorService) {
        this.userService = userService;
        this.doctorService = doctorService;
    }

    public LoginResponse login(LoginReq loginReq) {
        return userService.login(loginReq.getUsername(),loginReq.getPassword());

    }

    public DoctorResponse getDoctors() {
        return doctorService.getDoctorsByClinicId();
    }
}
