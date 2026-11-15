package com.myclinic.backend.pharmacy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pharmacy")
public class PharmacyController {

    @GetMapping("/greet")
    public String helloPharma(){
        return "Hello pharma!...";
    }
}
