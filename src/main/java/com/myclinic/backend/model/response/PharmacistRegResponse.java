package com.myclinic.backend.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacistRegResponse {

    private Long clinicId;
    private String clinicName;
    private Long pharmacistId;
    private String pharmacistName;
    private String pharmacistContactNumber;
    private String pharmacistEmail;

}
