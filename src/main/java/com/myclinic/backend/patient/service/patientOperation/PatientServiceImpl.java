package com.myclinic.backend.patient.service.patientOperation;

import com.myclinic.backend.admin.exceptions.InvalidRequestExceptions;
import com.myclinic.backend.admin.util.ClinicUtil;
import com.myclinic.backend.patient.model.request.PatientRegistrationRequest;
import com.myclinic.backend.auth.core.ContextUtil;
import com.myclinic.backend.patient.entity.PatientDetails;
import com.myclinic.backend.patient.model.response.PatientRegistrationResponse;
import com.myclinic.backend.patient.model.response.PatientClinicRecordResponse;
import com.myclinic.backend.patient.service.rephandler.PatientAllergyRepoHandler;
import com.myclinic.backend.patient.service.rephandler.PatientMedicationRepoHandler;
import com.myclinic.backend.patient.service.rephandler.PatientImmunizationRepoHandler;
import com.myclinic.backend.patient.service.rephandler.PatientRepoHandler;
import com.myclinic.backend.patient.transform.PatientMapper;
import com.myclinic.backend.patient.util.PatientValidator;
import org.springframework.stereotype.Service;

import static com.myclinic.backend.patient.constants.PatientConstants.USER_NOT_ASSOCIATED_WITH_CLINIC;
import static com.myclinic.backend.patient.constants.PatientConstants.PATIENT_NOT_FOUND;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepoHandler patientRepoHandler;
    private final PatientValidator patientValidator;
    private final ContextUtil contextUtil;
    private final ClinicUtil clinicUtil;
    private final PatientAllergyRepoHandler allergyRepoHandler;
    private final PatientMedicationRepoHandler medicationRepoHandler;
    private final PatientImmunizationRepoHandler immunizationRepoHandler;


    public PatientServiceImpl(PatientRepoHandler patientRepoHandler, PatientValidator patientValidator, ContextUtil contextUtil, ClinicUtil clinicUtil,
                              PatientAllergyRepoHandler allergyRepoHandler, PatientMedicationRepoHandler medicationRepoHandler,
                              PatientImmunizationRepoHandler immunizationRepoHandler) {
        this.patientRepoHandler = patientRepoHandler;
        this.patientValidator = patientValidator;
        this.contextUtil = contextUtil;
        this.clinicUtil = clinicUtil;
        this.allergyRepoHandler = allergyRepoHandler;
        this.medicationRepoHandler = medicationRepoHandler;
        this.immunizationRepoHandler = immunizationRepoHandler;
    }


    @Override
    public PatientClinicRecordResponse getPatientDetailsByClinicIdAndPatientId(String clinicId, Long patientId) {
        String userRole = contextUtil.getRoles().stream().findFirst().orElse("");
        if (!clinicUtil.validateUserInClinic(clinicId, contextUtil.getUserId(), userRole)) {
            throw new InvalidRequestExceptions(USER_NOT_ASSOCIATED_WITH_CLINIC);
        }

        PatientDetails patient = patientRepoHandler.findPatientByClinicIdAndPatientId(clinicId, patientId)
                .orElseThrow(() -> new InvalidRequestExceptions(PATIENT_NOT_FOUND));

        //todo : direct entity to response mapping is not a good practice, need to create a mapper for this
        //todo: fetch the consultation details for the patient and add it to the response
        //todo: first find the patient consultation details by patientId and clinicId, then with that consultationId, fetch the consultation details and add it to the response
        PatientClinicRecordResponse response = new PatientClinicRecordResponse();
        response.setPatient(PatientMapper.constructPatientResponse(patient));
        response.setAllergies(allergyRepoHandler.findByClinicAndPatient(clinicId, patientId));
        response.setMedications(medicationRepoHandler.findByClinicAndPatient(clinicId, patientId));
        response.setImmunizations(immunizationRepoHandler.findByClinicAndPatient(clinicId, patientId));
        return response;
    }


    @Override
    public PatientRegistrationResponse registerPatient(PatientRegistrationRequest patientRegistrationRequest) {
        //validate request
        patientValidator.validatePatientRegistrationRequest(patientRegistrationRequest);

        //validate the user
        String userRole = contextUtil.getRoles().stream().findFirst().orElse("");
        if (!clinicUtil.validateUserInClinic(patientRegistrationRequest.getClinicId(), contextUtil.getUserId(), userRole)) {
            throw new InvalidRequestExceptions(USER_NOT_ASSOCIATED_WITH_CLINIC);
        }

        PatientDetails patientDetails = PatientMapper.constructPatientDetails(patientRegistrationRequest, contextUtil);
        PatientDetails registeredPatient = patientRepoHandler.savePatientDetails(patientDetails);
        return PatientMapper.constructPatientResponse(registeredPatient);
    }



    @Override
    public void addToConsultationQueue(String patientId, String doctorId) {

    }
}
