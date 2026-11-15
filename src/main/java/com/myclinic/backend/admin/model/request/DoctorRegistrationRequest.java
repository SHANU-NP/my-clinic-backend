package com.myclinic.backend.admin.model.request;

import lombok.Data;

@Data
public class DoctorRegistrationRequest {
    private Long clinicId;
    private String doctorName;
    private String designation;
    private String specialization;
    private String username;
    private String password;

}
