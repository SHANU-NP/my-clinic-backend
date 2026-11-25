package com.myclinic.backend.patient.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationQueueResponse {
    private LocalDate consultationDate;
    private String consultationNotes;
    private String doctorId;
    private String doctorName;
    private String doctorDesignation;
    private String session;
    private int tokenNumber;
    private String status;
}
