package com.myclinic.backend.service;

import com.myclinic.backend.entity.Clinic;
import com.myclinic.backend.model.request.ClinicRegistrationRequest;
import com.myclinic.backend.model.response.ClinicRegistrationResponse;
import com.myclinic.backend.util.AdminValidator;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final AdminRepositoryHandler adminRepositoryHandler;
    private final AdminValidator adminValidator;

    public AdminService(AdminRepositoryHandler adminRepositoryHandler, AdminValidator adminValidator) {
        this.adminRepositoryHandler = adminRepositoryHandler;
        this.adminValidator = adminValidator;
    }


    public ClinicRegistrationResponse registerClinic(ClinicRegistrationRequest request) {

        adminValidator.validateClinicRegistrationRequest(request);

        Clinic newClinic = new Clinic();
        newClinic.setName(request.getName());
        newClinic.setAddress(request.getAddress());
        newClinic.setContactNumber(request.getContactNumber());
        newClinic.setEmail(request.getEmail());

        return adminRepositoryHandler.registerClinic(newClinic);
    }
}
