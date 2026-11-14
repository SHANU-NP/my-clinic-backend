package com.myclinic.backend.service;

import com.myclinic.backend.model.request.PatientRegistrationRequest;
import com.myclinic.backend.service.repositoryHandler.PatientRepoHandler;
import com.myclinic.backend.util.PatientValidator;
import org.springframework.stereotype.Service;

@Service
public class PatientService {


    private final PatientRepoHandler patientRepoHandler;
    private final PatientValidator patientValidator;

    public PatientService(PatientRepoHandler patientRepoHandler, PatientValidator patientValidator) {
        this.patientRepoHandler = patientRepoHandler;
        this.patientValidator = patientValidator;
    }


    private void createPatient(PatientRegistrationRequest patientRegistrationRequest){
        // identify the user and the clinic information from the request and validate the request

    }

}
