package com.myclinic.backend.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "user_authentication")
public class UserAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ser_seq")
    @SequenceGenerator(name = "ser_seq", sequenceName = "ser_seq", allocationSize = 1)
    private Long serialId;

    @Column(name = "user_id", nullable = false, unique = true)
    private UUID userId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;


}
