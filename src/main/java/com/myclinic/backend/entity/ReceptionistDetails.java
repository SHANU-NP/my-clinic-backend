package com.myclinic.backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "receptionist_details")
public class ReceptionistDetails extends  DBTimeStamp{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(name = "clinic_id", nullable = false)
    private String clinicId;

    @Column(name = "clinic_name",nullable = false)
    private String clinicName;

    @Column(nullable = false)
    private String name;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(nullable = false)
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

}
