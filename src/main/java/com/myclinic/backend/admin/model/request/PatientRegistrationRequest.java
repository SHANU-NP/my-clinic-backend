package com.myclinic.backend.admin.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRegistrationRequest {

    private String firstName;
    private String lastName;
    private String age;
    private String gender;
    private String contactNumber;
    private String email;
    private String address;
}
