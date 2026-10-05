package com.myclinic.backend.util;

import com.myclinic.backend.exceptions.InvalidRequestExceptions;
import com.myclinic.backend.model.request.ClinicRegistrationRequest;
import com.myclinic.backend.model.request.DoctorRegistrationRequest;
import com.myclinic.backend.model.request.PharmacistRegistrationRequest;
import com.myclinic.backend.model.request.ReceptionistRegistrationRequest;
import org.springframework.stereotype.Component;

@Component
public class AdminValidator {




    public void validateClinicRegistrationRequest(ClinicRegistrationRequest request) {
      validateNotBlank("name", request.getName());
      validateNotBlank("contactNumber", request.getContactNumber());

    }


    private void validateNotBlank(String fieldName,String value){
        if (value.isBlank()) {
            throw new InvalidRequestExceptions(String.format(" %s can not be blank ", fieldName));
        }
    }

    public void validateDoctorRegistrationRequest(DoctorRegistrationRequest request) {
        validateNotBlank("doctorName", request.getDoctorName());
        validateNotBlank("designation", request.getDesignation());
        validateNotBlank("specialization", request.getSpecialization());
        validateNotBlank("clinicId", String.valueOf(request.getClinicId()));


    }

    public void validateReceptionistRegistrationRequest(ReceptionistRegistrationRequest request){
        validateNotBlank("receptionistName", request.getReceptionistName());
        validateNotBlank("receptionistContactNumber",request.getContactNumber());
        validateNotBlank("receptionistEmail", request.getEmail());
        validateNotBlank("clinicId", String.valueOf(request.getClinicId()));

    }


    public void validatePharmacistRegistrationRequest(PharmacistRegistrationRequest request) {
            validateNotBlank("pharmacistName", request.getPharmacistName());
            validateNotBlank("pharmacistContactNumber",request.getContactNumber());
            validateNotBlank("pharmacistEmail", request.getEmail());
            validateNotBlank("clinicId", String.valueOf(request.getClinicId()));
    }
}
