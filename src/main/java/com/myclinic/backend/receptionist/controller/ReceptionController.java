package com.myclinic.backend.receptionist.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/reception")
public class ReceptionController {

    @GetMapping("/greet")
    public String helloReceptionist(){
        return "Hello receptionist!...";
    }

}
