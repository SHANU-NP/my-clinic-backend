package com.myclinic.backend.auth.service.userDetail;

import com.myclinic.backend.auth.constants.AuthConstants;
import com.myclinic.backend.auth.entity.UserAuth;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

    private final String userId;
    private final String password;
    private final String authorities;

    public CustomUserDetails(UserAuth user) {
        this.userId = user.getUserId().toString();
        this.password = user.getPassword();
        this.authorities = user.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(this.authorities.split(","))
                .map(String::trim)
                .map(role -> new SimpleGrantedAuthority(AuthConstants.ROLE_PREFIX + role))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }
}
