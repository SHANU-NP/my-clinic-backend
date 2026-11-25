package com.myclinic.backend.patient.service.patientOperation;

import com.myclinic.backend.patient.model.request.ConsultationRequest;
import com.myclinic.backend.patient.model.request.PatientRegistrationRequest;
import com.myclinic.backend.patient.model.response.ConsultationQueueResponse;
import com.myclinic.backend.patient.model.response.PatientConsultationResponse;
import com.myclinic.backend.patient.model.response.PatientRegistrationResponse;
import com.myclinic.backend.patient.model.response.PatientClinicRecordResponse;
import com.myclinic.backend.patient.model.response.QueuePatientResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

public interface PatientService {

    PatientRegistrationResponse registerPatient(PatientRegistrationRequest patientRegistrationRequest);
    PatientClinicRecordResponse getPatientDetailsByClinicIdAndPatientId(String clinicId, Long patientId);
    ConsultationQueueResponse addToConsultationQueue(ConsultationRequest consultationRequest);
    Page<QueuePatientResponse> getQueuePatients(LocalDate consultationDate, String session, Pageable pageable);



}
