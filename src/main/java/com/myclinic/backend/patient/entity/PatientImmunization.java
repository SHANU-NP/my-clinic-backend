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
@Table(name = "patient_immunization", indexes = {
        @Index(name = "idx_patient_immunization_clinic_patient", columnList = "clinic_id, patient_id")
})
public class PatientImmunization extends DBTimeStamp {

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

    @Column(name = "vaccine_name", nullable = false)
    private String vaccineName;

    private String dose;
    private String administeredDate;
    private String nextDueDate;
    private String administeredBy;
    private String lotNumber;
    private String notes;
}
