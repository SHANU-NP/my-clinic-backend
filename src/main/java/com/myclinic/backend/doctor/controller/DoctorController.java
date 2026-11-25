package com.myclinic.backend.doctor.controller;

import com.myclinic.backend.auth.model.response.LoginResponse;
import com.myclinic.backend.auth.model.request.LoginReq;
import com.myclinic.backend.doctor.model.response.DoctorProfileResponse;
import com.myclinic.backend.doctor.service.DoctorService;
import com.myclinic.backend.patient.model.request.ConsultationRequest;
import com.myclinic.backend.patient.model.request.PatientRegistrationRequest;
import com.myclinic.backend.patient.model.response.ConsultationQueueResponse;
import com.myclinic.backend.patient.model.response.PatientRegistrationResponse;
import com.myclinic.backend.patient.model.response.PatientClinicRecordResponse;
import com.myclinic.backend.patient.model.response.QueuePatientResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/v1/doctor")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/greet")
    public String helloDoctor(){
        return "Hello doctor!...";
    }

    @PostMapping("/login")
    @SecurityRequirements
    public LoginResponse login(@RequestBody LoginReq loginReq){
        return doctorService.login(loginReq);
    }

    @GetMapping("/profile")
    public DoctorProfileResponse getProfile() {
        return doctorService.getProfile();
    }


    @PostMapping("/register/patient")
    public PatientRegistrationResponse registerPatient(@RequestBody PatientRegistrationRequest request){
        return doctorService.registerPatient(request);
    }

    @GetMapping("/clinic/{clinicId}/patients/{patientId}")
    public PatientClinicRecordResponse getPatientDetails(@PathVariable String clinicId, @PathVariable Long patientId) {
        return doctorService.getPatientDetailsByClinicIdAndPatientId(clinicId, patientId);
    }

    @GetMapping("/queue/patients")
    public Page<QueuePatientResponse> getQueuePatients(@RequestParam LocalDate consultationDate,
                                                       @RequestParam String session,
                                                       Pageable pageable) {
        return doctorService.getQueuePatients(consultationDate, session, pageable);
    }

    //add to consultation queue endpoint by doctor
     @PostMapping("/queue/patients")
     public ConsultationQueueResponse addToConsultationQueue(@RequestBody ConsultationRequest consultationRequest) {
         return doctorService.addToConsultationQueue(consultationRequest);
     }




}
