package com.myclinic.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "doctor_details")
public class DoctorDetails extends  DBTimeStamp {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_seq")
    @SequenceGenerator(name = "doctor_seq", sequenceName = "doctor_seq",initialValue = 6000, allocationSize = 1)
    private Long id;

    @Column(name = "clinic_id", nullable = false)
    private String clinicId;

    @Column(name = "clinic_name",nullable = false)
    private String clinicName;

    @Column(name = "name", nullable = false)
    private String doctorName;

    @Column(name = "designation")
    private String designation;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "role")
    private String role;

}
