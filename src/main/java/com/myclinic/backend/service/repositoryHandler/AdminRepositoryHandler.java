package com.myclinic.backend.service.repositoryHandler;

import com.myclinic.backend.constants.AdminConstants;
import com.myclinic.backend.entity.Clinic;
import com.myclinic.backend.entity.DoctorDetails;
import com.myclinic.backend.entity.PharmacistDetails;
import com.myclinic.backend.entity.ReceptionistDetails;
import com.myclinic.backend.model.response.ClinicRegistrationResponse;
import com.myclinic.backend.model.response.DoctorRegistrationResponse;
import com.myclinic.backend.model.response.PharmacistRegResponse;
import com.myclinic.backend.model.response.ReceptionistRegistrationResponse;
import com.myclinic.backend.repository.ClinicRepository;
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

    public AdminRepositoryHandler(ClinicRepository clinicRepository, DoctorRepositoryHandler doctorRepositoryHandler, ReceptionistRepositoryHandler receptionistRepositoryHandler, PharmacistRepoHandler pharmacistRepositoryHandler) {
        this.clinicRepository = clinicRepository;
        this.doctorRepositoryHandler = doctorRepositoryHandler;
        this.receptionistRepositoryHandler = receptionistRepositoryHandler;
        this.pharmacistRepositoryHandler = pharmacistRepositoryHandler;
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
