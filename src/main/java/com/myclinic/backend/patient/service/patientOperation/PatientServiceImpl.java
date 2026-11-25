package com.myclinic.backend.patient.service.patientOperation;

import com.myclinic.backend.admin.exceptions.InvalidRequestExceptions;
import com.myclinic.backend.admin.util.ClinicUtil;
import com.myclinic.backend.patient.constants.PatientConstants;
import com.myclinic.backend.patient.entity.ConsultationSchedule;
import com.myclinic.backend.patient.model.request.ConsultationRequest;
import com.myclinic.backend.patient.model.request.PatientRegistrationRequest;
import com.myclinic.backend.auth.core.ContextUtil;
import com.myclinic.backend.patient.entity.PatientDetails;
import com.myclinic.backend.patient.model.response.ConsultationQueueResponse;
import com.myclinic.backend.patient.model.response.PatientRegistrationResponse;
import com.myclinic.backend.patient.model.response.PatientClinicRecordResponse;
import com.myclinic.backend.patient.model.response.QueuePatientResponse;
import com.myclinic.backend.patient.service.rephandler.*;
import com.myclinic.backend.patient.transform.PatientMapper;
import com.myclinic.backend.patient.transform.PatientClinicalRecordMapper;
import com.myclinic.backend.patient.util.PatientValidator;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.myclinic.backend.patient.constants.PatientConstants.*;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepoHandler patientRepoHandler;
    private final PatientValidator patientValidator;
    private final ContextUtil contextUtil;
    private final ClinicUtil clinicUtil;
    private final PatientAllergyRepoHandler allergyRepoHandler;
    private final PatientMedicationRepoHandler medicationRepoHandler;
    private final PatientImmunizationRepoHandler immunizationRepoHandler;
    private final PatientDoctorConsultationDetailRepoHandler consultationRepoHandler;
    private final ConsultationScheduleRepoHandler consultationScheduleRepoHandler;


    public PatientServiceImpl(PatientRepoHandler patientRepoHandler, PatientValidator patientValidator, ContextUtil contextUtil, ClinicUtil clinicUtil,
                              PatientAllergyRepoHandler allergyRepoHandler, PatientMedicationRepoHandler medicationRepoHandler,
                              PatientImmunizationRepoHandler immunizationRepoHandler,
                              PatientDoctorConsultationDetailRepoHandler consultationRepoHandler, ConsultationScheduleRepoHandler consultationScheduleRepoHandler) {
        this.patientRepoHandler = patientRepoHandler;
        this.patientValidator = patientValidator;
        this.contextUtil = contextUtil;
        this.clinicUtil = clinicUtil;
        this.allergyRepoHandler = allergyRepoHandler;
        this.medicationRepoHandler = medicationRepoHandler;
        this.immunizationRepoHandler = immunizationRepoHandler;
        this.consultationRepoHandler = consultationRepoHandler;
        this.consultationScheduleRepoHandler = consultationScheduleRepoHandler;
    }


    @Override
    public PatientClinicRecordResponse getPatientDetailsByClinicIdAndPatientId(String clinicId, Long patientId) {
        String userRole = contextUtil.getRoles().stream().findFirst().orElse("");
        if (!clinicUtil.doctorValidationInClinic(clinicId, contextUtil.getUserId())) {
            throw new InvalidRequestExceptions(DOCTOR_NOT_ASSOCIATED_WITH_CLINIC);
        }

        PatientDetails patient = patientRepoHandler.findPatientByClinicIdAndPatientId(clinicId, patientId)
                .orElseThrow(() -> new InvalidRequestExceptions(PATIENT_NOT_FOUND));

        PatientClinicRecordResponse response = new PatientClinicRecordResponse();
        response.setPatient(PatientMapper.constructPatientResponse(patient));
        response.setConsultations(consultationRepoHandler.findByClinicAndPatient(clinicId, patientId).stream()
                .map(PatientClinicalRecordMapper::toResponse).collect(Collectors.toList()));

        response.setAllergies(allergyRepoHandler.findByClinicAndPatient(clinicId, patientId).stream()
                .map(PatientClinicalRecordMapper::toResponse).collect(Collectors.toList()));
        response.setMedications(medicationRepoHandler.findByClinicAndPatient(clinicId, patientId).stream()
                .map(PatientClinicalRecordMapper::toResponse).collect(Collectors.toList()));
        response.setImmunizations(immunizationRepoHandler.findByClinicAndPatient(clinicId, patientId).stream()
                .map(PatientClinicalRecordMapper::toResponse).collect(Collectors.toList()));
        return response;
    }




    @Override
    public PatientRegistrationResponse registerPatient(PatientRegistrationRequest patientRegistrationRequest) {
        //validate request
        patientValidator.validatePatientRegistrationRequest(patientRegistrationRequest);

        //validate the user
        String userRole = contextUtil.getRoles().stream().findFirst().orElse("");
        if (!clinicUtil.validateUserGeneralValidationInClinic(patientRegistrationRequest.getClinicId(), contextUtil.getUserId(), userRole)) {
            throw new InvalidRequestExceptions(USER_NOT_ASSOCIATED_WITH_CLINIC);
        }

        PatientDetails patientDetails = PatientMapper.constructPatientDetails(patientRegistrationRequest, contextUtil);
        PatientDetails registeredPatient = patientRepoHandler.savePatientDetails(patientDetails);
        return PatientMapper.constructPatientResponse(registeredPatient);
    }



    @Override
    public ConsultationQueueResponse addToConsultationQueue(ConsultationRequest consultationRequest) {
        String doctorId = consultationRequest.getDoctorId();
        String clinicId = contextUtil.getTenantId();
        String userRole = contextUtil.getRoles().stream().findFirst().orElse("");
        String userId = contextUtil.getUserId();
        String session = consultationRequest.getSession();
        LocalDate consultationDate = consultationRequest.getConsultationDate();
        String patientId = consultationRequest.getPatientId();

        PatientDetails patientDetails = patientRepoHandler.findPatientByClinicIdAndPatientId(clinicId, Long.parseLong(patientId))
                .orElseThrow(() -> new InvalidRequestExceptions(PATIENT_NOT_FOUND));
        if (session == null || (!session.equals(PatientConstants.MORNING) && !session.equals(PatientConstants.AFTERNOON) && !session.equals(PatientConstants.EVENING))) {
            throw new InvalidRequestExceptions(INVALID_SESSION);
        }

        //check if the user is associated with the clinic and has the required role to add a patient to the consultation queue
        if (clinicUtil.validateUserGeneralValidationInClinic(contextUtil.getTenantId(), contextUtil.getUserId(),userRole)) {
            List<ConsultationSchedule> consultationList = consultationScheduleRepoHandler.findAllTokensByClinicIdAndDoctorIdAndConsultationDate(
                    clinicId, doctorId,consultationDate);


            //if patient already present in the queue for the same session, return the existing consultation details
            ConsultationSchedule consultationScheduleOfPatient =  consultationList.stream()
                    .filter(c -> c.getPatientId().equals(Long.parseLong(patientId)) && c.getSession().equals(session))
                    .findFirst()
                    .orElse(null);

            if (consultationScheduleOfPatient != null) {
               throw new InvalidRequestExceptions(PATIENT_ALREADY_IN_QUEUE);
            }

            consultationList.sort((c1, c2) -> Integer.compare(c2.getTokenNumber(), c1.getTokenNumber()));
            int nextTokenNumber = consultationList.isEmpty() ? 1 : consultationList.get(0).getTokenNumber() + 1;

            //adding the patient to the consultation queue
            ConsultationSchedule consultationSchedule = new ConsultationSchedule();
            consultationSchedule.setPatientId(Long.parseLong(patientId));
            consultationSchedule.setDoctorId(doctorId);
            consultationSchedule.setClinicId(clinicId);
            consultationSchedule.setConsultationDate(consultationDate);
            consultationSchedule.setSession(session);
            consultationSchedule.setTokenNumber(nextTokenNumber);
            consultationSchedule.setCreatedBy(userId);
            consultationSchedule.setPatientName(patientDetails.getFirstName() + " " + patientDetails.getLastName());
            consultationSchedule.setPatientContactNumber(patientDetails.getContactNumber());
            consultationSchedule.setStatus(PatientConstants.SCHEDULED);
            consultationSchedule.setConsultationNotes(consultationRequest.getConsultationNotes());

            consultationScheduleRepoHandler.saveConsultationSchedule(consultationSchedule);
            return new ConsultationQueueResponse(
                    consultationSchedule.getConsultationDate(),
                    consultationSchedule.getConsultationNotes(),
                    consultationSchedule.getDoctorId(),
                    consultationSchedule.getDoctorName(),
                    consultationSchedule.getDoctorDesignation(),
                    consultationSchedule.getSession(),
                    consultationSchedule.getTokenNumber(),
                    consultationSchedule.getStatus()
            );
        } else {
            throw new InvalidRequestExceptions(USER_NOT_ASSOCIATED_WITH_CLINIC);
        }
    }

    @Override
    public Page<QueuePatientResponse> getQueuePatients(LocalDate consultationDate, String session, Pageable pageable) {
        String clinicId = contextUtil.getTenantId();
        String doctorId = contextUtil.getUserId();
        String userRole = contextUtil.getRoles().stream().findFirst().orElse("");

        if (consultationDate == null) {
            throw new InvalidRequestExceptions(PatientConstants.CONSULTATION_DATE_REQUIRED);
        }
        if (session == null || (!session.equals(PatientConstants.MORNING)
                && !session.equals(PatientConstants.AFTERNOON) && !session.equals(PatientConstants.EVENING))) {
            throw new InvalidRequestExceptions(INVALID_SESSION);
        }

        if (!clinicUtil.validateUserGeneralValidationInClinic(clinicId, doctorId, userRole)) {
            throw new InvalidRequestExceptions(USER_NOT_ASSOCIATED_WITH_CLINIC);
        }

        return consultationScheduleRepoHandler.findQueuePatients(clinicId, doctorId, consultationDate, session, pageable)
                .map(schedule -> new QueuePatientResponse(schedule.getId(), schedule.getPatientId(),
                        schedule.getPatientName(), schedule.getPatientContactNumber(), schedule.getPatientAddress(),
                        schedule.getConsultationDate(), schedule.getSession(), schedule.getTokenNumber(),
                        schedule.getStatus(), schedule.getConsultationNotes()));
    }
}
