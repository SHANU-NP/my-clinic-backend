package com.myclinic.backend.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicRegistrationRequest {
    private String name;
    private String address;
    private String contactNumber;
    private String email;
}
