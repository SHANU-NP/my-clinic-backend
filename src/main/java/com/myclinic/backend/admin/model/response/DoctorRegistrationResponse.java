package com.myclinic.backend.admin.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRegistrationResponse {

    private Long clinicId;
    private String clinicName;
    private Long doctorId;
    private String doctorName;
    private String designation;
    private String specialization;
}
