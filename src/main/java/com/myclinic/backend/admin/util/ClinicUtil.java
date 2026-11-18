package com.myclinic.backend.admin.util;

import com.myclinic.backend.doctor.repository.DoctorRepository;
import com.myclinic.backend.pharmacy.repository.PharmacistRepository;
import com.myclinic.backend.receptionist.repository.ReceptionistRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ClinicUtil {

    private final DoctorRepository doctorRepository;
    private final ReceptionistRepository receptionistRepository;
    private final PharmacistRepository pharmacistRepository;

    public ClinicUtil(DoctorRepository doctorRepository, ReceptionistRepository receptionistRepository,
                      PharmacistRepository pharmacistRepository) {
        this.doctorRepository = doctorRepository;
        this.receptionistRepository = receptionistRepository;
        this.pharmacistRepository = pharmacistRepository;
    }

    public boolean validateUserInClinic(String clinicId, String userId, String role) {
        UUID userUuid;
        try {
            userUuid = UUID.fromString(userId);
        } catch (IllegalArgumentException exception) {
            return false;
        }

        return switch (role) {
            case "DOCTOR" -> doctorRepository.existsByIdAndClinicId(userUuid, clinicId);
            case "RECEPTIONIST" -> receptionistRepository.existsByIdAndClinicId(userUuid, clinicId);
            case "PHARMACIST" -> pharmacistRepository.existsByIdAndClinicId(userUuid, clinicId);
            default -> false;
        };
    }
}
