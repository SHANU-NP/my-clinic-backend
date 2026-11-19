package com.myclinic.backend.admin.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceptionistRegistrationResponse {

    private UUID clinicId;
    private String clinicName;
    private UUID receptionistId;
    private String receptionistName;
    private String contactNumber;
    private String email;
}
