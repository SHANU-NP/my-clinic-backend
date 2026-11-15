package com.myclinic.backend.doctor.model.request;

import lombok.Data;

@Data
public class LoginReq {

    private String username;
    private String password;
}
