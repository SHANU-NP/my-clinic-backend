package com.myclinic.backend.service;

import com.myclinic.backend.entity.Clinic;
import com.myclinic.backend.entity.DoctorDetails;
import com.myclinic.backend.entity.PharmacistDetails;
import com.myclinic.backend.entity.ReceptionistDetails;
import com.myclinic.backend.exceptions.InvalidRequestExceptions;
import com.myclinic.backend.model.request.*;
import com.myclinic.backend.model.response.*;
import com.myclinic.backend.service.repositoryHandler.AdminRepositoryHandler;
import com.myclinic.backend.util.AdminValidator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.myclinic.backend.constants.AdminConstants.*;

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
        return clinics.stream().map(clinic -> new ClinicResponse(clinic.getId(), clinic.getName())).collect(Collectors.toList());
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
        newDoctor.setRole(ROLE_DOCTOR);
        return adminRepositoryHandler.registerDoctor(newDoctor);
    }

    public ReceptionistRegistrationResponse registerReceptionist(ReceptionistRegistrationRequest request){
        adminValidator.validateReceptionistRegistrationRequest(request);

        Clinic clinic = adminRepositoryHandler.findClinicById(request.getClinicId())
                .orElseThrow(() -> new InvalidRequestExceptions(CLINIC_NOT_FOUND));

        ReceptionistDetails newReceptionist = new ReceptionistDetails();
        newReceptionist.setName(request.getReceptionistName());
        newReceptionist.setContactNumber(request.getContactNumber());
        newReceptionist.setEmail(request.getEmail());
        newReceptionist.setClinicId(clinic.getId().toString());
        newReceptionist.setClinicName(clinic.getName());
        newReceptionist.setRole(ROLE_RECEPTIONIST);
        return adminRepositoryHandler.registerReceptionist(newReceptionist);

    }


    public PharmacistRegResponse registerPharmacist(PharmacistRegistrationRequest request) {
        adminValidator.validatePharmacistRegistrationRequest(request);

        Clinic clinic = adminRepositoryHandler.findClinicById(request.getClinicId())
                .orElseThrow(() -> new InvalidRequestExceptions(CLINIC_NOT_FOUND));

        PharmacistDetails newPharmacist = new PharmacistDetails();
        newPharmacist.setName(request.getPharmacistName());
        newPharmacist.setContactNumber(request.getContactNumber());
        newPharmacist.setEmail(request.getEmail());
        newPharmacist.setClinicId(clinic.getId().toString());
        newPharmacist.setClinicName(clinic.getName());
        newPharmacist.setRole(ROLE_PHARMACIST);
        return adminRepositoryHandler.registerPharmacist(newPharmacist);

    }
}
