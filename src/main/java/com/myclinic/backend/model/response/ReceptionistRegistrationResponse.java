package com.myclinic.backend.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceptionistRegistrationResponse {

    private Long clinicId;
    private String clinicName;
    private Long receptionistId;
    private String receptionistName;
    private String contactNumber;
    private String email;


}
