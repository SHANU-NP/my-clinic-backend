package com.myclinic.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Table(name = "pharmacist_details")
public class PharmacistDetails extends  DBTimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prmst_seq")
    @SequenceGenerator(name = "prmst_seq", sequenceName = "prmst_seq",initialValue = 9000, allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(nullable = false)
    private String email;

    @Column(name = "clinic_id", nullable = false)
    private String clinicId;

    @Column(name = "clinic_name",nullable = false)
    private String clinicName;

    @Column(name = "role")
    private String role;
}
