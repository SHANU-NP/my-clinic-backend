package com.myclinic.backend.admin.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRegistrationResponse {

    private Long clinicId;
    private String clinicName;
    private UUID doctorId;
    private String doctorName;
    private String designation;
    private String specialization;
}
