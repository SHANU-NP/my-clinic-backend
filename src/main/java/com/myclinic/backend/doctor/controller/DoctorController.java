package com.myclinic.backend.doctor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/doctor")
public class DoctorController {


    @GetMapping("/greet")
    public String helloDoctor(){
        return "Hello doctor!...";
    }






}
