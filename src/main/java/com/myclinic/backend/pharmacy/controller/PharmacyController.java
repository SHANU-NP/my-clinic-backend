package com.myclinic.backend.pharmacy.controller;

import com.myclinic.backend.auth.model.response.LoginResponse;
import com.myclinic.backend.auth.model.request.LoginReq;
import com.myclinic.backend.pharmacy.service.PharmacyService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pharmacy")
public class PharmacyController {

    private final PharmacyService pharmacyService;

    public PharmacyController(PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping("/greet")
    public String helloPharma(){
        return "Hello pharma!...";
    }

    @PostMapping("/login")
    @SecurityRequirements
    public LoginResponse login(@RequestBody LoginReq loginReq){
        return pharmacyService.login(loginReq);
    }
}
