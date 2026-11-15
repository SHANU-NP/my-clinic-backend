package com.myclinic.backend.patient.service;

import com.myclinic.backend.admin.model.request.PatientRegistrationRequest;
import com.myclinic.backend.patient.service.rephandler.PatientRepoHandler;
import com.myclinic.backend.patient.util.PatientValidator;
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
