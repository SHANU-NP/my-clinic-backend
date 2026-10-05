package com.myclinic.backend.controller;

import com.myclinic.backend.model.request.ClinicFetchRequest;
import com.myclinic.backend.model.request.ClinicRegistrationRequest;
import com.myclinic.backend.model.request.DoctorRegistrationRequest;
import com.myclinic.backend.model.request.ReceptionistRegistrationRequest;
import com.myclinic.backend.model.response.ClinicRegistrationResponse;
import com.myclinic.backend.model.response.ClinicResponse;
import com.myclinic.backend.model.response.DoctorRegistrationResponse;
import com.myclinic.backend.model.response.ReceptionistRegistrationResponse;
import com.myclinic.backend.service.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/admin")
public class AdminController {


    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register-clinic")
    public ClinicRegistrationResponse registerClinic(@RequestBody ClinicRegistrationRequest request) {
        return adminService.registerClinic(request);
    }

    @GetMapping("/fetch-clinic")
    public List<ClinicResponse> fetchClinic(@RequestBody ClinicFetchRequest request){
        return adminService.fetchClinic(request);
    }

    @PostMapping("/register-doctor")
    public DoctorRegistrationResponse registerDoctor(@RequestBody DoctorRegistrationRequest request) {
        return adminService.registerDoctor(request);
    }

    @PostMapping ("/register-receptionist")
        public ReceptionistRegistrationResponse registerReceptionist(@RequestBody ReceptionistRegistrationRequest request){
        return adminService.registerReceptionist(request);
        }


}
