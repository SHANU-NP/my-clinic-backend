package com.myclinic.backend.patient.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myclinic.backend.admin.entity.DBTimeStamp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "patient_medication", indexes = {
        @Index(name = "idx_patient_medication_clinic_patient", columnList = "clinic_id, patient_id")
})
public class PatientMedication extends DBTimeStamp {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clinic_id", nullable = false, length = 36)
    private String clinicId;

    @Column(name = "patient_id", nullable = false)
    private Long patientId;

    @Column(name = "consultation_id", nullable = false)
    private UUID consultationId;

    @Column(name = "medication_name", nullable = false)
    private String medicationName;

    private String dosage;
    private String route;
    private String frequency;
    private String startDate;
    private String endDate;
    private String prescribedBy;
    private String status;
    private String instructions;
    private String notes;
}
