package com.myclinic.backend.service.repositoryHandler;

import com.myclinic.backend.entity.DoctorDetails;
import com.myclinic.backend.model.response.DoctorRegistrationResponse;
import com.myclinic.backend.repository.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorRepositoryHandler {

    private final DoctorRepository doctorRepository;

    public DoctorRepositoryHandler(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public DoctorRegistrationResponse registerDoctor(DoctorDetails newDoctor) {
        DoctorDetails savedDoctor = doctorRepository.save(newDoctor);
        return new DoctorRegistrationResponse(
                Long.valueOf(savedDoctor.getClinicId()),
                savedDoctor.getClinicName(),
                savedDoctor.getId(),
                savedDoctor.getDoctorName(),
                savedDoctor.getDesignation(),
                savedDoctor.getSpecialization()
        );
    }
}
