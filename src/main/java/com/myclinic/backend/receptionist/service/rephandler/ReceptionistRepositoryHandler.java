package com.myclinic.backend.receptionist.service.rephandler;

import com.myclinic.backend.receptionist.entity.ReceptionistDetails;
import com.myclinic.backend.admin.model.response.ReceptionistRegistrationResponse;
import com.myclinic.backend.receptionist.repository.ReceptionistRepository;
import org.springframework.stereotype.Service;

@Service
public class ReceptionistRepositoryHandler {
    private final ReceptionistRepository receptionistRepository;

    public ReceptionistRepositoryHandler(ReceptionistRepository receptionistRepository) {
        this.receptionistRepository = receptionistRepository;
    }

    public ReceptionistRegistrationResponse registerReceptionist(ReceptionistDetails newReceptionist) {
        ReceptionistDetails saved = receptionistRepository.save(newReceptionist);
        return new ReceptionistRegistrationResponse(
                Long.valueOf(saved.getClinicId()),
                saved.getClinicName(),
                saved.getId(),
                saved.getName(),
                saved.getContactNumber(),
                saved.getEmail()
        );
    }
}
