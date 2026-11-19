package com.myclinic.backend.patient.entity;


import com.myclinic.backend.admin.entity.DBTimeStamp;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "consultation_schedule")
public class ConsultationSchedule extends DBTimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long patientId;
    private Long doctorId;
    private String clinicId;
    private String consultationDate;
    private String consultationTime;
    private String consultationNotes;
    private String status; // Scheduled, Completed, Cancelled


}
