package com.myclinic.backend.patient.model.response;

import java.util.UUID;

public record PatientMedicationResponse(
        UUID consultationId,
        String medicationName,
        String dosage,
        String route,
        String frequency,
        String startDate,
        String endDate,
        String prescribedBy,
        String status,
        String instructions,
        String notes
) {}
