package com.myclinic.backend.patient.service.patientOperation;

import com.myclinic.backend.admin.model.request.PatientRegistrationRequest;
import com.myclinic.backend.patient.service.rephandler.PatientRepoHandler;
import com.myclinic.backend.patient.util.PatientValidator;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {


    private final PatientRepoHandler patientRepoHandler;
    private final PatientValidator patientValidator;

    public PatientServiceImpl(PatientRepoHandler patientRepoHandler, PatientValidator patientValidator) {
        this.patientRepoHandler = patientRepoHandler;
        this.patientValidator = patientValidator;
    }


    @Override
    public void registerPatient(PatientRegistrationRequest patientRegistrationRequest) {

    }

    @Override
    public void addToConsultationQueue(String patientId, String doctorId) {

    }
}
