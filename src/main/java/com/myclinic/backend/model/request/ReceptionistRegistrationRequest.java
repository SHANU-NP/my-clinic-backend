package com.myclinic.backend.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceptionistRegistrationRequest {
    private Long clinicId;
    private String receptionistName;
    private String contactNumber;
    private String email;

}
