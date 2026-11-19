package com.myclinic.backend.patient.service.patientOperation;

import com.myclinic.backend.patient.model.request.PatientRegistrationRequest;
import com.myclinic.backend.patient.model.response.PatientRegistrationResponse;
import com.myclinic.backend.patient.model.response.PatientClinicRecordResponse;
import org.springframework.stereotype.Component;

@Component
public interface PatientService {

    PatientRegistrationResponse registerPatient(PatientRegistrationRequest patientRegistrationRequest);
    PatientClinicRecordResponse getPatientDetailsByClinicIdAndPatientId(String clinicId, Long patientId);
    void addToConsultationQueue(String patientId, String doctorId);



}
