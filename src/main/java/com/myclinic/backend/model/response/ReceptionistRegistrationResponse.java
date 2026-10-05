package com.myclinic.backend.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceptionistRegistrationResponse {

    private Long receptionistId;
    private String receptionistName;
    private String receptionistContactNumber;
    private String email;
    private String clinicId;
    private String clinicName;

}
