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
@Table(name = "patient_allergy", indexes = {
        @Index(name = "idx_patient_allergy_clinic_patient", columnList = "clinic_id, patient_id")
})
public class PatientAllergy extends DBTimeStamp {

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

    @Column(name = "allergen", nullable = false)
    private String allergen;

    private String reaction;
    private String severity;
    private String onsetDate;
    private String status;
    private String notes;
}
