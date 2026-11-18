package com.myclinic.backend.patient.service.patientOperation;

import com.myclinic.backend.admin.entity.ClinicUserMap;
import com.myclinic.backend.admin.exceptions.InvalidRequestExceptions;
import com.myclinic.backend.admin.servce.repoHandler.ClinicUserMapRepositoryHandler;
import com.myclinic.backend.admin.util.ClinicUtil;
import com.myclinic.backend.patient.model.request.PatientRegistrationRequest;
import com.myclinic.backend.auth.core.ContextUtil;
import com.myclinic.backend.patient.entity.PatientDetails;
import com.myclinic.backend.patient.model.response.PatientRegistrationResponse;
import com.myclinic.backend.patient.service.rephandler.PatientRepoHandler;
import com.myclinic.backend.patient.transform.PatientMapper;
import com.myclinic.backend.patient.util.PatientValidator;
import org.springframework.stereotype.Service;

import static com.myclinic.backend.patient.constants.PatientConstants.USER_NOT_ACTIVE;
import static com.myclinic.backend.patient.constants.PatientConstants.USER_NOT_ASSOCIATED_WITH_CLINIC;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepoHandler patientRepoHandler;
    private final PatientValidator patientValidator;
    private final ContextUtil contextUtil;
    private final ClinicUtil clinicUtil;


    public PatientServiceImpl(PatientRepoHandler patientRepoHandler, PatientValidator patientValidator, ContextUtil contextUtil, ClinicUtil clinicUtil) {
        this.patientRepoHandler = patientRepoHandler;
        this.patientValidator = patientValidator;
        this.contextUtil = contextUtil;
        this.clinicUtil = clinicUtil;
    }


    @Override
    public PatientRegistrationResponse registerPatient(PatientRegistrationRequest patientRegistrationRequest) {
        //validate request
        patientValidator.validatePatientRegistrationRequest(patientRegistrationRequest);

        //validate the user
        if (!clinicUtil.validateUserActiveInClinic(patientRegistrationRequest.getClinicId(), contextUtil.getUserId())) {
            throw new InvalidRequestExceptions(USER_NOT_ACTIVE);
        }

        PatientDetails patientDetails = PatientMapper.constructPatientDetails(patientRegistrationRequest, contextUtil);
        PatientDetails registeredPatient = patientRepoHandler.savePatientDetails(patientDetails);
        return PatientMapper.constructPatientResponse(registeredPatient);
    }



    @Override
    public void addToConsultationQueue(String patientId, String doctorId) {

    }
}
