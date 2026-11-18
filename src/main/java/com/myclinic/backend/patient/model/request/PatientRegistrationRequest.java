package com.myclinic.backend.patient.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRegistrationRequest {

    private String clinicId;
    private String firstName;
    private String lastName;
    private String dob;
    private String gender;
    private String contactNumber;
    private String email;
    private String address;
    private String bloodGroup;
    private String emergencyContactName;
    private String emergencyContactNumber;
}
