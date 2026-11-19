package com.myclinic.backend.patient.model.response;

import java.util.UUID;

public record PatientAllergyResponse(
        UUID consultationId,
        String allergen,
        String reaction,
        String severity,
        String onsetDate,
        String status,
        String notes
) {}
