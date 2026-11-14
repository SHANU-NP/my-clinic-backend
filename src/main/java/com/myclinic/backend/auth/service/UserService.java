package com.myclinic.backend.auth.service;

import com.myclinic.backend.auth.model.response.LoginResponse;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    LoginResponse login(String username, String password);


}
