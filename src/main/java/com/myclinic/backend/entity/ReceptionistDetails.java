package com.myclinic.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "receptionist_details")
public class ReceptionistDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rsp_seq")
    @SequenceGenerator(name = "rsp_seq", sequenceName = "rsp_seq",initialValue = 3000, allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(nullable = false)
    private String email;

    @Column(name = "clinic_id")
    private String clinicId;

    @Column(name = "clinic_name",nullable = false)
    private String clinicName;

    @Column(name = "role")
    private String role;

}
