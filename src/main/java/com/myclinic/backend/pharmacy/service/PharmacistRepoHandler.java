package com.myclinic.backend.pharmacy.service;

import com.myclinic.backend.pharmacy.entity.PharmacistDetails;
import com.myclinic.backend.admin.model.response.PharmacistRegResponse;
import com.myclinic.backend.pharmacy.repository.PharmacistRepository;
import org.springframework.stereotype.Service;

@Service
public class PharmacistRepoHandler {

    private final PharmacistRepository pharmacistRepository;

    public PharmacistRepoHandler(PharmacistRepository pharmacistRepository) {
        this.pharmacistRepository = pharmacistRepository;
    }

    public PharmacistRegResponse registerPharmacist(PharmacistDetails newPharmacist) {
        PharmacistDetails saved = pharmacistRepository.save(newPharmacist);
        return new PharmacistRegResponse(
                Long.valueOf(saved.getClinicId()),
                saved.getClinicName(),
                saved.getId(),
                saved.getName(),
                saved.getContactNumber(),
                saved.getEmail());
    }
}
