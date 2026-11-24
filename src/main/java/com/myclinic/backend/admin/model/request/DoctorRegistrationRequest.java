package com.myclinic.backend.admin.model.request;

import java.util.UUID;

import lombok.Data;

@Data
public class DoctorRegistrationRequest {
    private UUID clinicId;
    private String doctorName;
    private String designation;
    private String specialization;
    private String username;
    private String password;
    private String contactNumber;

}
