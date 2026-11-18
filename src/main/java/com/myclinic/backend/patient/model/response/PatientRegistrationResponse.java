package com.myclinic.backend.patient.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRegistrationResponse {
    private String patientId;
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
