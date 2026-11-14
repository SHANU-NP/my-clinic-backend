package com.myclinic.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "doctor_details")
public class DoctorDetails extends  DBTimeStamp {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "clinic_id", nullable = false)
    private String clinicId;

    @Column(name = "clinic_name",nullable = false)
    private String clinicName;

    @Column(name = "name", nullable = false)
    private String doctorName;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "designation")
    private String designation;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "role")
    private String role;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;


}
