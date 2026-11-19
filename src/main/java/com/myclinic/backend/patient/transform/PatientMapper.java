package com.myclinic.backend.patient.transform;


import com.myclinic.backend.auth.core.ContextUtil;
import com.myclinic.backend.patient.entity.PatientDetails;
import com.myclinic.backend.patient.model.request.PatientRegistrationRequest;
import com.myclinic.backend.patient.model.response.PatientRegistrationResponse;

import java.time.LocalDate;

import static com.myclinic.backend.patient.constants.PatientConstants.PATIENT;

public class PatientMapper {
    public static PatientDetails constructPatientDetails(PatientRegistrationRequest patientRegistrationRequest, ContextUtil contextUtil) {
        PatientDetails patient = new PatientDetails();
        patient.setFirstName(patientRegistrationRequest.getFirstName());
        patient.setLastName(patientRegistrationRequest.getLastName());
        patient.setEmail(patientRegistrationRequest.getEmail());
        patient.setContactNumber(patientRegistrationRequest.getContactNumber());
        patient.setAddress(patientRegistrationRequest.getAddress());
        patient.setBloodGroup(patientRegistrationRequest.getBloodGroup());
        patient.setClinicId(contextUtil.getTenantId());
        patient.setEmergencyContactName(patientRegistrationRequest.getEmergencyContactName());
        patient.setEmergencyContactNumber(patientRegistrationRequest.getEmergencyContactNumber());
        patient.setDob(LocalDate.parse(patientRegistrationRequest.getDob()));
        patient.setGender(patientRegistrationRequest.getGender());
        patient.setRole(PATIENT);
        return patient;
    }

    public static PatientRegistrationResponse constructPatientResponse(PatientDetails registeredPatient) {
        PatientRegistrationResponse response = new PatientRegistrationResponse();
        response.setPatientId(registeredPatient.getId().toString());
        response.setFirstName(registeredPatient.getFirstName());
        response.setLastName(registeredPatient.getLastName());
        response.setEmail(registeredPatient.getEmail());
        response.setContactNumber(registeredPatient.getContactNumber());
        response.setAddress(registeredPatient.getAddress());
        response.setBloodGroup(registeredPatient.getBloodGroup());
        response.setClinicId(registeredPatient.getClinicId());
        response.setEmergencyContactName(registeredPatient.getEmergencyContactName());
        response.setEmergencyContactNumber(registeredPatient.getEmergencyContactNumber());
        return response;
    }
}
