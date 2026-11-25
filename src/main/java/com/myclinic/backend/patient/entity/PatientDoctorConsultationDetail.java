package com.myclinic.backend.patient.entity;


import com.myclinic.backend.admin.entity.DBTimeStamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "patient_doctor_consultation_detail")
public class PatientDoctorConsultationDetail extends DBTimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID consultationId;

    private LocalDate consultationDate;
    private LocalTime consultationTime;
    private String consultationNotes;

    private String clinicId;
    private Long patientId;
    private String doctorId;
    private String doctorName;
    private String doctorDesignation;
    private String session; // MORNING, AFTERNOON, EVENING
    private int tokenNumber;
    private String status; // Scheduled, Completed, Cancelled

}
