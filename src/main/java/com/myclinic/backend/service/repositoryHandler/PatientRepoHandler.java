package com.myclinic.backend.service.repositoryHandler;

import com.myclinic.backend.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientRepoHandler {

    private final PatientRepository patientRepository;

    public PatientRepoHandler(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
}
