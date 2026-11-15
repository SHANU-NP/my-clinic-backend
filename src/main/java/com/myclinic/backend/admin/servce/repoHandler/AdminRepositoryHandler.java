package com.myclinic.backend.admin.servce.repoHandler;

import com.myclinic.backend.auth.service.repoHandler.UserAuthRepoHandler;
import com.myclinic.backend.admin.constants.AdminConstants;
import com.myclinic.backend.admin.entity.Clinic;
import com.myclinic.backend.doctor.entity.DoctorDetails;
import com.myclinic.backend.pharmacy.entity.PharmacistDetails;
import com.myclinic.backend.receptionist.entity.ReceptionistDetails;
import com.myclinic.backend.admin.model.response.ClinicRegistrationResponse;
import com.myclinic.backend.admin.model.response.DoctorRegistrationResponse;
import com.myclinic.backend.admin.model.response.PharmacistRegResponse;
import com.myclinic.backend.admin.model.response.ReceptionistRegistrationResponse;
import com.myclinic.backend.admin.repo.ClinicRepository;
import com.myclinic.backend.doctor.service.rephandler.DoctorRepositoryHandler;
import com.myclinic.backend.pharmacy.service.PharmacistRepoHandler;
import com.myclinic.backend.receptionist.service.rephandler.ReceptionistRepositoryHandler;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AdminRepositoryHandler {

    private final ClinicRepository clinicRepository;
    private final DoctorRepositoryHandler doctorRepositoryHandler;
    private final ReceptionistRepositoryHandler receptionistRepositoryHandler;
    private final PharmacistRepoHandler pharmacistRepositoryHandler;
    private final UserAuthRepoHandler userAuthRepoHandler;

    public AdminRepositoryHandler(ClinicRepository clinicRepository, DoctorRepositoryHandler doctorRepositoryHandler, ReceptionistRepositoryHandler receptionistRepositoryHandler, PharmacistRepoHandler pharmacistRepositoryHandler, UserAuthRepoHandler userAuthRepoHandler) {
        this.clinicRepository = clinicRepository;
        this.doctorRepositoryHandler = doctorRepositoryHandler;
        this.receptionistRepositoryHandler = receptionistRepositoryHandler;
        this.pharmacistRepositoryHandler = pharmacistRepositoryHandler;
        this.userAuthRepoHandler = userAuthRepoHandler;
    }


    public ClinicRegistrationResponse registerClinic(Clinic newClinic) {
        Clinic savedClinic = clinicRepository.save(newClinic);
        return new ClinicRegistrationResponse(savedClinic.getId(),savedClinic.getName(),AdminConstants.CLINIC_REGISTRATION_SUCCESSFUL);
    }


    public Optional<Clinic> findClinicById(Long clinicId) {
        return clinicRepository.findById(clinicId);
    }


    public List<Clinic> findByNameContainingIgnoreCase(String clinicName) {
        return clinicRepository.findByNameContainingIgnoreCase(clinicName);
    }

    public Collection<? extends Clinic> findAll() {
        return clinicRepository.findAll();
    }

    public DoctorRegistrationResponse registerDoctor(DoctorDetails newDoctor) {
        return doctorRepositoryHandler.registerDoctor(newDoctor);
    }

    public ReceptionistRegistrationResponse registerReceptionist(ReceptionistDetails newReceptionist){
        return  receptionistRepositoryHandler.registerReceptionist(newReceptionist);
    }

    public PharmacistRegResponse registerPharmacist(PharmacistDetails newPharmacist) {
        return pharmacistRepositoryHandler.registerPharmacist(newPharmacist);
    }
}
