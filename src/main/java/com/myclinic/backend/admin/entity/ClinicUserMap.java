package com.myclinic.backend.admin.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clinic_user_map",uniqueConstraints = {@UniqueConstraint(columnNames = {"clinicId", "userId"}, name = "unique_clinic_user_map")})
public class ClinicUserMap {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String clinicId;
    private String userId;
    private String role;
    private boolean active;

}
