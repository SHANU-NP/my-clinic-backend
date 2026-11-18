package com.myclinic.backend.admin.controller;

import com.myclinic.backend.admin.model.request.*;
import com.myclinic.backend.admin.model.response.*;
import com.myclinic.backend.admin.servce.AdminService;
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

    @PostMapping("/register-pharmacist")
    public PharmacistRegResponse registerPharmacist(@RequestBody PharmacistRegistrationRequest request){
        return adminService.registerPharmacist(request);
    }

}
