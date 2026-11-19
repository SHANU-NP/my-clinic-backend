package com.myclinic.backend.admin.model.request;

import java.util.UUID;

import lombok.Data;

@Data
public class PharmacistRegistrationRequest {

    private UUID clinicId;
    private String pharmacistName;
    private String contactNumber;
    private String email;
    private String username;
    private String password;

}
