package com.myclinic.backend.patient.model.request;


import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationRequest{
    private String patientId;
    private String doctorId;
    private LocalDate consultationDate;
    private String session;
    private String consultationNotes;

}
