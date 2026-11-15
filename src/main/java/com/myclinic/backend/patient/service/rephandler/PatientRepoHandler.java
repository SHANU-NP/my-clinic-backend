package com.myclinic.backend.patient.service.rephandler;

import com.myclinic.backend.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientRepoHandler {

    private final PatientRepository patientRepository;

    public PatientRepoHandler(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
}
