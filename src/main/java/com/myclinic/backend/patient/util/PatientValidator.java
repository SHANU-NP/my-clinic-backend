package com.myclinic.backend.patient.util;

import com.myclinic.backend.admin.exceptions.InvalidRequestExceptions;
import com.myclinic.backend.patient.model.request.PatientRegistrationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PatientValidator {

    public void validatePatientRegistrationRequest(PatientRegistrationRequest patientRegistrationRequest) {
        validateNotBlank("firstName", patientRegistrationRequest.getFirstName());
        validateNotBlank("lastName", patientRegistrationRequest.getLastName());
        validateNotBlank("dob", patientRegistrationRequest.getDob());
        validateNotBlank("gender", patientRegistrationRequest.getGender());
        validateNotBlank("contactNumber", patientRegistrationRequest.getContactNumber());
        validateNotBlank("address", patientRegistrationRequest.getAddress());
    }


    private void validateNotBlank(String fieldName,String value){
        try{
            if (value.isBlank()) {
                throw new InvalidRequestExceptions(String.format(" %s can not be blank ", fieldName));
            }
        }catch (Exception ex){
            ex.printStackTrace();
            log.error("error : {}",ex.toString());
        }

    }
}