package com.myclinic.backend.model.request;

import lombok.Data;

@Data
public class PharmacistRegistrationRequest {

    private Long clinicId;
    private String pharmacistName;
    private String contactNumber;
    private String email;

}
