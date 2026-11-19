package com.myclinic.backend.patient.model.response;

import lombok.Data;

import java.util.List;

@Data
public class PatientClinicRecordResponse {
    private PatientRegistrationResponse patient;
    private List<PatientConsultationResponse> consultations;
    private List<PatientAllergyResponse> allergies;
    private List<PatientMedicationResponse> medications;
    private List<PatientImmunizationResponse> immunizations;
}
