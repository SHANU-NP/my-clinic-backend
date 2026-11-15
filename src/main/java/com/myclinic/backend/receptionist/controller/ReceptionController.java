package com.myclinic.backend.receptionist.controller;

import com.myclinic.backend.auth.model.request.LoginReq;
import com.myclinic.backend.auth.model.response.LoginResponse;
import com.myclinic.backend.receptionist.service.ReceptionistService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/reception")
public class ReceptionController {

    private final ReceptionistService receptionistService;

    public ReceptionController(ReceptionistService receptionistService) {
        this.receptionistService = receptionistService;
    }

    @GetMapping("/greet")
    public String helloReceptionist(){
        return "Hello receptionist!...";
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginReq loginReq){
        return receptionistService.login(loginReq);
    }

}
