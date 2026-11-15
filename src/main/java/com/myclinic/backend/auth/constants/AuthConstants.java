package com.myclinic.backend.auth.constants;

public class AuthConstants {

    private AuthConstants() {}

    public static final String ROLE_PREFIX = "ROLE_";

    public static final String USER_NOT_FOUND = "User not found in the system";
    public static final String INVALID_CREDENTIALS = "Invalid username or password";
    public static final String LOGIN_FAILED = "Login failed, please try after sometime";
    public static final String USERNAME_ALREADY_EXIST = "Username already exists, please try another one";

    public static final String BEARER_PREFIX = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String ROLES_CLAIM = "roles";
    public static final String TENANT_ID_CLAIM = "tenantId";
}
