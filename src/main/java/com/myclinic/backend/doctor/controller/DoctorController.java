package com.myclinic.backend.doctor.controller;

import com.myclinic.backend.auth.model.response.LoginResponse;
import com.myclinic.backend.auth.model.request.LoginReq;
import com.myclinic.backend.doctor.service.DoctorService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:50284")
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
    public LoginResponse login(@RequestBody LoginReq loginReq){
        return doctorService.login(loginReq);
    }





}
