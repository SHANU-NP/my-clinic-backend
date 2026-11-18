package com.myclinic.backend.patient.entity;

import com.myclinic.backend.admin.entity.DBTimeStamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "patient_details")
public class PatientDetails extends DBTimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ptn_seq")
    @SequenceGenerator(name = "ptn_seq", sequenceName = "ptn_seq", initialValue = 600, allocationSize = 1)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    private String dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "clinic_id", nullable = false)
    private String clinicId;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "emergency_contact_name")
    private String emergencyContactName;

    @Column(name = "emergency_contact_number")
    private String emergencyContactNumber;
}
