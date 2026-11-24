package com.myclinic.backend.admin.servce;

import com.myclinic.backend.admin.entity.Clinic;
import com.myclinic.backend.admin.model.request.*;
import com.myclinic.backend.admin.model.response.*;
import com.myclinic.backend.auth.entity.UserAuth;
import com.myclinic.backend.auth.service.repoHandler.UserAuthRepoHandler;
import com.myclinic.backend.doctor.entity.DoctorDetails;
import com.myclinic.backend.pharmacy.entity.PharmacistDetails;
import com.myclinic.backend.receptionist.entity.ReceptionistDetails;
import com.myclinic.backend.admin.exceptions.InvalidRequestExceptions;
import com.myclinic.backend.admin.servce.repoHandler.AdminRepositoryHandler;
import com.myclinic.backend.admin.util.AdminValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.myclinic.backend.admin.constants.AdminConstants.*;
import static com.myclinic.backend.auth.constants.AuthConstants.USERNAME_ALREADY_EXIST;

@Service
public class AdminService {

    private final AdminRepositoryHandler adminRepositoryHandler;
    private final AdminValidator adminValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserAuthRepoHandler userAuthRepoHandler;

    public AdminService(AdminRepositoryHandler adminRepositoryHandler, AdminValidator adminValidator, BCryptPasswordEncoder bCryptPasswordEncoder, UserAuthRepoHandler userAuthRepoHandler) {
        this.adminRepositoryHandler = adminRepositoryHandler;
        this.adminValidator = adminValidator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userAuthRepoHandler = userAuthRepoHandler;
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



    @Transactional
    public DoctorRegistrationResponse registerDoctor(DoctorRegistrationRequest request) {

        adminValidator.validateDoctorRegistrationRequest(request);

        if (userAuthRepoHandler.existsByUsername(request.getUsername())) {
            throw new InvalidRequestExceptions(USERNAME_ALREADY_EXIST);
        }

        Clinic clinic = adminRepositoryHandler.findClinicById(request.getClinicId())
                .orElseThrow(() -> new InvalidRequestExceptions(CLINIC_NOT_FOUND));

        // 1. Create UserAuth first
        UserAuth userAuth = new UserAuth();
        userAuth.setUsername(request.getUsername());
        userAuth.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userAuth.setRole(ROLE_DOCTOR);
        userAuth.setTenantId(clinic.getId().toString());
        UserAuth savedAuth = userAuthRepoHandler.save(userAuth);

        // 2. Create domain entity with same UUID
        DoctorDetails newDoctor = new DoctorDetails();
        newDoctor.setId(savedAuth.getUserId());
        newDoctor.setClinicId(clinic.getId().toString());
        newDoctor.setClinicName(clinic.getName());
        newDoctor.setDoctorName(request.getDoctorName());
        newDoctor.setDesignation(request.getDesignation());
        newDoctor.setSpecialization(request.getSpecialization());
        newDoctor.setContactNumber(request.getContactNumber());
        return adminRepositoryHandler.registerDoctor(newDoctor);
    }



    @Transactional
    public ReceptionistRegistrationResponse registerReceptionist(ReceptionistRegistrationRequest request) {
        adminValidator.validateReceptionistRegistrationRequest(request);

        if (userAuthRepoHandler.existsByUsername(request.getUsername())) {
            throw new InvalidRequestExceptions(USERNAME_ALREADY_EXIST);
        }

        Clinic clinic = adminRepositoryHandler.findClinicById(request.getClinicId())
                .orElseThrow(() -> new InvalidRequestExceptions(CLINIC_NOT_FOUND));

        // 1. Create UserAuth first
        UserAuth userAuth = new UserAuth();
        userAuth.setUsername(request.getUsername());
        userAuth.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userAuth.setRole(ROLE_RECEPTIONIST);
        userAuth.setTenantId(clinic.getId().toString());
        UserAuth savedAuth = userAuthRepoHandler.save(userAuth);

        // 2. Create domain entity with same UUID
        ReceptionistDetails newReceptionist = new ReceptionistDetails();
        newReceptionist.setId(savedAuth.getUserId());
        newReceptionist.setName(request.getReceptionistName());
        newReceptionist.setContactNumber(request.getContactNumber());
        newReceptionist.setEmail(request.getEmail());
        newReceptionist.setClinicId(clinic.getId().toString());
        newReceptionist.setClinicName(clinic.getName());
        return adminRepositoryHandler.registerReceptionist(newReceptionist);
    }



    @Transactional
    public PharmacistRegResponse registerPharmacist(PharmacistRegistrationRequest request) {
        adminValidator.validatePharmacistRegistrationRequest(request);

        if (userAuthRepoHandler.existsByUsername(request.getUsername())) {
            throw new InvalidRequestExceptions(USERNAME_ALREADY_EXIST);
        }

        Clinic clinic = adminRepositoryHandler.findClinicById(request.getClinicId())
                .orElseThrow(() -> new InvalidRequestExceptions(CLINIC_NOT_FOUND));

        // 1. Create UserAuth first
        UserAuth userAuth = new UserAuth();
        userAuth.setUsername(request.getUsername());
        userAuth.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userAuth.setRole(ROLE_PHARMACIST);
        userAuth.setTenantId(clinic.getId().toString());
        UserAuth savedAuth = userAuthRepoHandler.save(userAuth);

        // 2. Create domain entity with same UUID
        PharmacistDetails newPharmacist = new PharmacistDetails();
        newPharmacist.setId(savedAuth.getUserId());
        newPharmacist.setName(request.getPharmacistName());
        newPharmacist.setContactNumber(request.getContactNumber());
        newPharmacist.setEmail(request.getEmail());
        newPharmacist.setClinicId(clinic.getId().toString());
        newPharmacist.setClinicName(clinic.getName());
        return adminRepositoryHandler.registerPharmacist(newPharmacist);
    }
}
