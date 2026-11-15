package com.myclinic.backend.auth.model.request;

import lombok.Data;

@Data
public class LoginReq {

    private String username;
    private String password;
}
