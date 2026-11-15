package com.myclinic.backend.auth.entity;

import com.myclinic.backend.admin.entity.DBTimeStamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "user_authentication")
public class UserAuth extends DBTimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    private UUID userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "tenant_id")
    private String tenantId;
}
