package com.myclinic.backend.service;

import com.myclinic.backend.constants.AdminConstants;
import com.myclinic.backend.entity.Clinic;
import com.myclinic.backend.entity.DoctorDetails;
import com.myclinic.backend.exceptions.InvalidRequestExceptions;
import com.myclinic.backend.model.request.ClinicFetchRequest;
import com.myclinic.backend.model.request.ClinicRegistrationRequest;
import com.myclinic.backend.model.request.DoctorRegistrationRequest;
import com.myclinic.backend.model.response.ClinicRegistrationResponse;
import com.myclinic.backend.model.response.ClinicResponse;
import com.myclinic.backend.model.response.DoctorRegistrationResponse;
import com.myclinic.backend.service.repositoryHandler.AdminRepositoryHandler;
import com.myclinic.backend.util.AdminValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.myclinic.backend.constants.AdminConstants.CLINIC_NOT_FOUND;

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

    public List<ClinicResponse> fetchClinic(ClinicFetchRequest request) {

        List<Clinic> clinics = new ArrayList<>();

            if (request.getClinicId() != null) {

                Optional<Clinic> clinic = adminRepositoryHandler.findClinicById(request.getClinicId());
                clinic.ifPresent(clinics::add);

            } else if (request.getClinicName() != null && !request.getClinicName().isEmpty()) {
                List<Clinic> clinicList = adminRepositoryHandler.findByNameContainingIgnoreCase(request.getClinicName());
                clinics.addAll(clinicList);

            } else {
                clinics.addAll(adminRepositoryHandler.findAll());
            }
        return clinics.stream().map(clinic -> new ClinicResponse(clinic.getId().toString(), clinic.getName())).collect(Collectors.toList());
    }

    public DoctorRegistrationResponse registerDoctor(DoctorRegistrationRequest request) {

        adminValidator.validateDoctorRegistrationRequest(request);

        Clinic clinic = adminRepositoryHandler.findClinicById(request.getClinicId())
                .orElseThrow(() -> new InvalidRequestExceptions(CLINIC_NOT_FOUND));

        DoctorDetails newDoctor = new DoctorDetails();
        newDoctor.setClinicId(clinic.getId().toString());
        newDoctor.setClinicName(clinic.getName());
        newDoctor.setDoctorName(request.getDoctorName());
        newDoctor.setDesignation(request.getDesignation());
        newDoctor.setSpecialization(request.getSpecialization());

        return adminRepositoryHandler.registerDoctor(newDoctor);
    }
}
