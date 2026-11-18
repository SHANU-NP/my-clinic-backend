package com.myclinic.backend.patient.service.patientOperation;

import com.myclinic.backend.patient.model.request.PatientRegistrationRequest;
import com.myclinic.backend.patient.model.response.PatientRegistrationResponse;
import org.springframework.stereotype.Component;

@Component
public interface PatientService {

    PatientRegistrationResponse registerPatient(PatientRegistrationRequest patientRegistrationRequest);
    void addToConsultationQueue(String patientId, String doctorId);



}
