package com.myclinic.backend.patient.model.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record PatientConsultationResponse(
        UUID consultationId,
        LocalDate consultationDate,
        LocalTime consultationTime,
        String consultationNotes,
        String doctorId
) {}
