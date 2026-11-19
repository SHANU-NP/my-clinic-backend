package com.myclinic.backend.doctor.service;

import com.myclinic.backend.auth.model.response.LoginResponse;
import com.myclinic.backend.auth.service.UserServiceImpl;
import com.myclinic.backend.auth.model.request.LoginReq;
import com.myclinic.backend.patient.model.request.PatientRegistrationRequest;
import com.myclinic.backend.patient.model.response.PatientRegistrationResponse;
import com.myclinic.backend.patient.model.response.PatientClinicRecordResponse;
import com.myclinic.backend.patient.service.patientOperation.PatientService;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final UserServiceImpl userService;
    private final PatientService patientService;

    public DoctorService(UserServiceImpl userService, PatientService patientService) {
        this.userService = userService;
        this.patientService = patientService;
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
}
