package com.myclinic.backend.service.repositoryHandler;

import com.myclinic.backend.constants.AdminConstants;
import com.myclinic.backend.entity.Clinic;
import com.myclinic.backend.entity.DoctorDetails;
import com.myclinic.backend.model.response.ClinicRegistrationResponse;
import com.myclinic.backend.model.response.DoctorRegistrationResponse;
import com.myclinic.backend.repository.ClinicRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AdminRepositoryHandler {

    private final ClinicRepository clinicRepository;
    private final DoctorRepositoryHandler doctorRepositoryHandler;

    public AdminRepositoryHandler(ClinicRepository clinicRepository, DoctorRepositoryHandler doctorRepositoryHandler) {
        this.clinicRepository = clinicRepository;
        this.doctorRepositoryHandler = doctorRepositoryHandler;
    }


    public ClinicRegistrationResponse registerClinic(Clinic newClinic) {
        Clinic savedClinic = clinicRepository.save(newClinic);
        return new ClinicRegistrationResponse(savedClinic.getId().toString(),savedClinic.getName(),AdminConstants.CLINIC_REGISTRATION_SUCCESSFUL);
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
}
