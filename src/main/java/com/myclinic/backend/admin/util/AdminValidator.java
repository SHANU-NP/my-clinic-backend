package com.myclinic.backend.admin.util;

import com.myclinic.backend.admin.exceptions.InvalidRequestExceptions;
import com.myclinic.backend.admin.model.request.ClinicRegistrationRequest;
import com.myclinic.backend.admin.model.request.DoctorRegistrationRequest;
import com.myclinic.backend.admin.model.request.PharmacistRegistrationRequest;
import com.myclinic.backend.admin.model.request.ReceptionistRegistrationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AdminValidator {




    public void validateClinicRegistrationRequest(ClinicRegistrationRequest request) {
      validateNotBlank("name", request.getName());
      validateNotBlank("contactNumber", request.getContactNumber());

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

    public void validateDoctorRegistrationRequest(DoctorRegistrationRequest request) {
        validateNotBlank("doctorName", request.getDoctorName());
        validateNotBlank("designation", request.getDesignation());
        validateNotBlank("specialization", request.getSpecialization());
        validateNotBlank("clinicId", String.valueOf(request.getClinicId()));
        validateNotBlank("username", request.getUsername());
        validateNotBlank("password", request.getPassword());


    }

    public void validateReceptionistRegistrationRequest(ReceptionistRegistrationRequest request){
        validateNotBlank("receptionistName", request.getReceptionistName());
        validateNotBlank("receptionistContactNumber",request.getContactNumber());
        validateNotBlank("receptionistEmail", request.getEmail());
        validateNotBlank("clinicId", String.valueOf(request.getClinicId()));
        validateNotBlank("username", request.getUsername());
        validateNotBlank("password", request.getPassword());

    }


    public void validatePharmacistRegistrationRequest(PharmacistRegistrationRequest request) {
            validateNotBlank("pharmacistName", request.getPharmacistName());
            validateNotBlank("pharmacistContactNumber",request.getContactNumber());
            validateNotBlank("pharmacistEmail", request.getEmail());
            validateNotBlank("clinicId", String.valueOf(request.getClinicId()));
            validateNotBlank("username", request.getUsername());
            validateNotBlank("password", request.getPassword());
    }
}
