package com.myclinic.backend.doctor.service;

import com.myclinic.backend.auth.core.ContextUtil;
import com.myclinic.backend.auth.model.response.LoginResponse;
import com.myclinic.backend.auth.service.UserServiceImpl;
import com.myclinic.backend.auth.model.request.LoginReq;
import com.myclinic.backend.admin.exceptions.InvalidRequestExceptions;
import com.myclinic.backend.doctor.entity.DoctorDetails;
import com.myclinic.backend.doctor.model.response.DoctorInfo;
import com.myclinic.backend.doctor.model.response.DoctorProfileResponse;
import com.myclinic.backend.doctor.model.response.DoctorResponse;
import com.myclinic.backend.doctor.service.rephandler.DoctorRepositoryHandler;
import com.myclinic.backend.patient.model.request.ConsultationRequest;
import com.myclinic.backend.patient.model.request.PatientRegistrationRequest;
import com.myclinic.backend.patient.model.response.ConsultationQueueResponse;
import com.myclinic.backend.patient.model.response.PatientRegistrationResponse;
import com.myclinic.backend.patient.model.response.PatientClinicRecordResponse;
import com.myclinic.backend.patient.model.response.QueuePatientResponse;
import com.myclinic.backend.patient.service.patientOperation.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.myclinic.backend.doctor.constants.DoctorConstants.DOCTOR_PROFILE_NOT_FOUND;

@Service
public class DoctorService {

    private final UserServiceImpl userService;
    private final PatientService patientService;
    private final ContextUtil contextUtil;
    private final DoctorRepositoryHandler doctorRepositoryHandler;

    public DoctorService(UserServiceImpl userService, PatientService patientService, ContextUtil contextUtil,
                         DoctorRepositoryHandler doctorRepositoryHandler) {
        this.userService = userService;
        this.patientService = patientService;
        this.contextUtil = contextUtil;
        this.doctorRepositoryHandler = doctorRepositoryHandler;
    }

    public LoginResponse login(LoginReq loginReq) {
        return userService.login(loginReq.getUsername(), loginReq.getPassword());
    }

    public DoctorProfileResponse getProfile() {
        DoctorDetails doctor = doctorRepositoryHandler.findById(contextUtil.getUserId())
                .orElseThrow(() -> new InvalidRequestExceptions(DOCTOR_PROFILE_NOT_FOUND));
        return new DoctorProfileResponse(doctor.getId().toString(), doctor.getDoctorName(), doctor.getContactNumber(),
                doctor.getDesignation(), doctor.getSpecialization(), doctor.getClinicId(), doctor.getClinicName()
        );
    }

    public DoctorResponse getDoctorsByClinicId() {
        String tenantId = contextUtil.getTenantId();
        return new DoctorResponse(doctorRepositoryHandler.findAllDoctorByClinicId(tenantId).stream()
                .map(doctor -> new DoctorInfo(doctor.getId().toString(), doctor.getDoctorName(),
                        doctor.getDesignation(), doctor.getSpecialization()))
                .toList());
    }


    public PatientRegistrationResponse registerPatient(PatientRegistrationRequest request) {
        return patientService.registerPatient(request);
    }

    public ConsultationQueueResponse addToConsultationQueue(ConsultationRequest consultationRequest) {
        return patientService.addToConsultationQueue(consultationRequest);
    }

    public Page<QueuePatientResponse> getQueuePatients(LocalDate consultationDate, String session, Pageable pageable) {
        return patientService.getQueuePatients(consultationDate, session, pageable);
    }

    public PatientClinicRecordResponse getPatientDetailsByClinicIdAndPatientId(String clinicId, Long patientId) {
        return patientService.getPatientDetailsByClinicIdAndPatientId(clinicId, patientId);
    }



}
