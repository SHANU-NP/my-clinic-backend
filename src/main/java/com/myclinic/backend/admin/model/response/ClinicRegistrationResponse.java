package com.myclinic.backend.admin.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicRegistrationResponse {

    private UUID clinicId;
    private String name;
    private String message;
}
