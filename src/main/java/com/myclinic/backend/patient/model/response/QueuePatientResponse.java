package com.myclinic.backend.patient.model.response;

import java.time.LocalDate;

public record QueuePatientResponse(
        Long scheduleId,
        Long patientId,
        String patientName,
        String patientContactNumber,
        String patientAddress,
        LocalDate consultationDate,
        String session,
        int tokenNumber,
        String status,
        String consultationNotes
) {}
