package com.myclinic.backend.patient.service.patientOperation;

import com.myclinic.backend.admin.model.request.PatientRegistrationRequest;
import org.springframework.stereotype.Component;

@Component
public interface PatientService {

    void registerPatient(PatientRegistrationRequest patientRegistrationRequest);
    void addToConsultationQueue(String patientId, String doctorId);



}
