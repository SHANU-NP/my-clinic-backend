package com.myclinic.backend.auth.service;

import com.myclinic.backend.auth.model.response.LoginResponse;

public interface UserService {

    LoginResponse login(String username, String password);
}
