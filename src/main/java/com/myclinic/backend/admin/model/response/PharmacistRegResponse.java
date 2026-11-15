package com.myclinic.backend.admin.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PharmacistRegResponse {

    private Long clinicId;
    private String clinicName;
    private UUID pharmacistId;
    private String pharmacistName;
    private String pharmacistContactNumber;
    private String pharmacistEmail;
}
