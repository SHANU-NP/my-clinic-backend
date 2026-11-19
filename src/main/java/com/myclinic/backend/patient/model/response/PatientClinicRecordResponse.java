package com.myclinic.backend.patient.model.response;

import com.myclinic.backend.patient.entity.PatientAllergy;
import com.myclinic.backend.patient.entity.PatientImmunization;
import com.myclinic.backend.patient.entity.PatientMedication;
import lombok.Data;

import java.util.List;

@Data
public class PatientClinicRecordResponse {
    private PatientRegistrationResponse patient;
    private List<PatientAllergy> allergies;
    private List<PatientMedication> medications;
    private List<PatientImmunization> immunizations;
}
