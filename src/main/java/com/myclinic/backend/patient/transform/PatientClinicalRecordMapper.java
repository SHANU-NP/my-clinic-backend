package com.myclinic.backend.patient.transform;

import com.myclinic.backend.patient.entity.PatientAllergy;
import com.myclinic.backend.patient.entity.PatientDoctorConsultationDetail;
import com.myclinic.backend.patient.entity.PatientImmunization;
import com.myclinic.backend.patient.entity.PatientMedication;
import com.myclinic.backend.patient.model.response.PatientAllergyResponse;
import com.myclinic.backend.patient.model.response.PatientConsultationResponse;
import com.myclinic.backend.patient.model.response.PatientImmunizationResponse;
import com.myclinic.backend.patient.model.response.PatientMedicationResponse;

public final class PatientClinicalRecordMapper {
    private PatientClinicalRecordMapper() {}

    public static PatientConsultationResponse toResponse(PatientDoctorConsultationDetail consultation) {
        return new PatientConsultationResponse(
                consultation.getConsultationId(), consultation.getConsultationDate(), consultation.getConsultationTime(),
                consultation.getConsultationNotes(), consultation.getDoctorId());
    }

    public static PatientAllergyResponse toResponse(PatientAllergy allergy) {
        return new PatientAllergyResponse(allergy.getConsultationId(), allergy.getAllergen(), allergy.getReaction(),
                allergy.getSeverity(), allergy.getOnsetDate(), allergy.getStatus(), allergy.getNotes());
    }

    public static PatientMedicationResponse toResponse(PatientMedication medication) {
        return new PatientMedicationResponse(medication.getConsultationId(), medication.getMedicationName(),
                medication.getDosage(), medication.getRoute(), medication.getFrequency(), medication.getStartDate(),
                medication.getEndDate(), medication.getPrescribedBy(), medication.getStatus(),
                medication.getInstructions(), medication.getNotes());
    }

    public static PatientImmunizationResponse toResponse(PatientImmunization immunization) {
        return new PatientImmunizationResponse(immunization.getConsultationId(), immunization.getVaccineName(),
                immunization.getDose(), immunization.getAdministeredDate(), immunization.getNextDueDate(),
                immunization.getAdministeredBy(), immunization.getLotNumber(), immunization.getNotes());
    }
}
