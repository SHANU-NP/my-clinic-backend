package com.myclinic.backend.doctor.service.rephandler;

import com.myclinic.backend.doctor.entity.DoctorDetails;
import com.myclinic.backend.admin.model.response.DoctorRegistrationResponse;
import com.myclinic.backend.doctor.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorRepositoryHandler {

    private final DoctorRepository doctorRepository;

    public DoctorRepositoryHandler(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public DoctorRegistrationResponse registerDoctor(DoctorDetails newDoctor) {
        DoctorDetails savedDoctor = doctorRepository.save(newDoctor);
        return new DoctorRegistrationResponse(
                java.util.UUID.fromString(savedDoctor.getClinicId()),
                savedDoctor.getClinicName(),
                savedDoctor.getId(),
                savedDoctor.getDoctorName(),
                savedDoctor.getDesignation(),
                savedDoctor.getSpecialization()
        );
    }

    public List<DoctorDetails> findAllDoctorByClinicId(String clinicId) {
        return doctorRepository.findAllByClinicId(clinicId);
    }
}
