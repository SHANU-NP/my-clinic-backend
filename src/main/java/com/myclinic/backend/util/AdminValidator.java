package com.myclinic.backend.util;

import com.myclinic.backend.exceptions.InvalidRequestExceptions;
import com.myclinic.backend.model.request.ClinicRegistrationRequest;
import org.springframework.stereotype.Component;

@Component
public class AdminValidator {




    public void validateClinicRegistrationRequest(ClinicRegistrationRequest request) {
      validateNotBlank("name", request.getName());
      validateNotBlank("contactNumber", request.getContactNumber());

    }


    private void validateNotBlank(String fieldName,String value){
        if (value.isBlank()) {
            throw new InvalidRequestExceptions(String.format("Clinic %s can not be blank ", fieldName));
        }
    }
}
