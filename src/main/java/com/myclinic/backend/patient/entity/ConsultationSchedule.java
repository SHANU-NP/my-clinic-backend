package com.myclinic.backend.patient.entity;


import com.myclinic.backend.admin.entity.DBTimeStamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Setter
@Getter
@Entity
@Table(name = "consultation_schedule")
public class ConsultationSchedule extends DBTimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long patientId;
    private Long doctorId;
    private String clinicId;
    private LocalDate consultationDate;
    private OffsetDateTime consultationAt;
    private String clinicTimeZone;
    private String consultationNotes;
    private String status; // Scheduled, Completed, Cancelled
    private String patientName;
    private String doctorName;
    private String doctorSpecialization;
    private String doctorDesignation;
    private String patientContactNumber;
    private String patientAddress;

}
