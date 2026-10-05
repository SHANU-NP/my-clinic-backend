package com.myclinic.backend.service.repositoryHandler;

import com.myclinic.backend.entity.PharmacistDetails;
import com.myclinic.backend.model.response.PharmacistRegResponse;
import com.myclinic.backend.repository.PharmacistRepository;
import org.springframework.stereotype.Service;

@Service
public class PharmacistRepoHandler {

    private final PharmacistRepository pharmacistRepository;

    public PharmacistRepoHandler(PharmacistRepository pharmacistRepository) {
        this.pharmacistRepository = pharmacistRepository;
    }

    public PharmacistRegResponse registerPharmacist(PharmacistDetails newPharmacist) {
            PharmacistDetails savedPharmacist = pharmacistRepository.save(newPharmacist);
            return new PharmacistRegResponse(
                    Long.valueOf(savedPharmacist.getClinicId()),
                    savedPharmacist.getClinicName(),
                    savedPharmacist.getId(),
                    savedPharmacist.getName(),
                    savedPharmacist.getContactNumber(),
                    savedPharmacist.getEmail());
    }
}
