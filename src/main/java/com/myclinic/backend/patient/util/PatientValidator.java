package com.myclinic.backend.patient.util;

import com.myclinic.backend.admin.exceptions.InvalidRequestExceptions;
import com.myclinic.backend.patient.model.request.PatientRegistrationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

import static com.myclinic.backend.patient.constants.PatientConstants.DOB_VALIDATION_ERROR;

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
        ValidateDOB(patientRegistrationRequest.getDob());
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

    private void ValidateDOB(String dob){
        // Implement DOB validation logic here
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(dob);
        } catch (Exception e) {
            throw new InvalidRequestExceptions(DOB_VALIDATION_ERROR);
        }
    }
}