package com.myclinic.backend.auth.service.userDetail;

import com.myclinic.backend.admin.constants.AdminConstants;
import com.myclinic.backend.auth.entity.UserAuth;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {


    private String username;
    private String password;
    private String authorites;

    public CustomUserDetails(UserAuth user) {
        this.username = user.getUserId().toString();
        this.password = user.getPassword();
        this.authorites = user.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(this.authorites.split(","))
                .map(String::trim)
                .map(role -> new SimpleGrantedAuthority(AdminConstants.ROLE_PREFIX+role))
                .collect(Collectors.toList());
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
