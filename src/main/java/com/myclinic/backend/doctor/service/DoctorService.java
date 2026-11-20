package com.myclinic.backend.doctor.service;

import com.myclinic.backend.auth.core.ContextUtil;
import com.myclinic.backend.auth.model.response.LoginResponse;
import com.myclinic.backend.auth.service.UserServiceImpl;
import com.myclinic.backend.auth.model.request.LoginReq;
import com.myclinic.backend.admin.exceptions.InvalidRequestExceptions;
import com.myclinic.backend.doctor.entity.DoctorDetails;
import com.myclinic.backend.doctor.model.response.DoctorProfileResponse;
import com.myclinic.backend.doctor.service.rephandler.DoctorRepositoryHandler;
import com.myclinic.backend.patient.model.request.PatientRegistrationRequest;
import com.myclinic.backend.patient.model.response.PatientRegistrationResponse;
import com.myclinic.backend.patient.model.response.PatientClinicRecordResponse;
import com.myclinic.backend.patient.service.patientOperation.PatientService;
import org.springframework.stereotype.Service;

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

    public PatientRegistrationResponse registerPatient(PatientRegistrationRequest request) {
        return patientService.registerPatient(request);
    }

    public PatientClinicRecordResponse getPatientDetailsByClinicIdAndPatientId(String clinicId, Long patientId) {
        return patientService.getPatientDetailsByClinicIdAndPatientId(clinicId, patientId);
    }

    public DoctorProfileResponse getProfile() {
        DoctorDetails doctor = doctorRepositoryHandler.findById(contextUtil.getUserId())
                .orElseThrow(() -> new InvalidRequestExceptions(DOCTOR_PROFILE_NOT_FOUND));
        return new DoctorProfileResponse(
                doctor.getId().toString(),
                doctor.getDoctorName(),
                doctor.getContactNumber(),
                doctor.getDesignation(),
                doctor.getSpecialization(),
                doctor.getClinicId(),
                doctor.getClinicName()
        );
    }
}
