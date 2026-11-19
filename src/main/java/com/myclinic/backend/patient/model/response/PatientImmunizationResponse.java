package com.myclinic.backend.patient.model.response;

import java.util.UUID;

public record PatientImmunizationResponse(
        UUID consultationId,
        String vaccineName,
        String dose,
        String administeredDate,
        String nextDueDate,
        String administeredBy,
        String lotNumber,
        String notes
) {}
