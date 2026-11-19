package com.myclinic.backend.admin.model.request;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceptionistRegistrationRequest {
    private UUID clinicId;
    private String receptionistName;
    private String contactNumber;
    private String email;
    private String username;
    private String password;

}
