package com.myclinic.backend.receptionist.entity;

import com.myclinic.backend.admin.entity.DBTimeStamp;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Setter
@Getter
@Table(name = "receptionist_details")
public class ReceptionistDetails extends DBTimeStamp {

    @Id
    private UUID id;

    @Column(name = "clinic_id", nullable = false)
    private String clinicId;

    @Column(name = "clinic_name", nullable = false)
    private String clinicName;

    @Column(nullable = false)
    private String name;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(nullable = false)
    private String email;
}
