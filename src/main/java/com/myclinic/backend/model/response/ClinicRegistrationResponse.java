package com.myclinic.backend.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicRegistrationResponse {

    private String clinicId;
    private String name;
    private String message;
}
