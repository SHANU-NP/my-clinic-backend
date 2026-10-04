package com.myclinic.backend.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRegistrationResponse {

    private String clinicId;
    private String clinicName;
    private String doctorId;
    private String doctorName;
    private String designation;
    private String specialization;
}
