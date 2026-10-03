package com.myclinic.backend.service;

import com.myclinic.backend.constants.AdminConstants;
import com.myclinic.backend.entity.Clinic;
import com.myclinic.backend.model.response.ClinicRegistrationResponse;
import com.myclinic.backend.repository.AdminRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminRepositoryHandler {

    private final AdminRepository adminRepository;

    public AdminRepositoryHandler(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    public ClinicRegistrationResponse registerClinic(Clinic newClinic) {
        Clinic savedClinic = adminRepository.save(newClinic);
        return new ClinicRegistrationResponse(savedClinic.getId().toString(),savedClinic.getName(),AdminConstants.CLINIC_REGISTRATION_SUCCESSFUL);
    }
}
