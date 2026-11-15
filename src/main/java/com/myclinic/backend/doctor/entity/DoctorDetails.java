package com.myclinic.backend.doctor.entity;

import com.myclinic.backend.admin.entity.DBTimeStamp;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "doctor_details")
public class DoctorDetails extends DBTimeStamp {

    @Id
    private UUID id;

    @Column(name = "clinic_id", nullable = false)
    private String clinicId;

    @Column(name = "clinic_name", nullable = false)
    private String clinicName;

    @Column(name = "name", nullable = false)
    private String doctorName;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "designation")
    private String designation;

    @Column(name = "specialization")
    private String specialization;
}
