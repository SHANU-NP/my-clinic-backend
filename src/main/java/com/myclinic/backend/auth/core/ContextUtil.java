package com.myclinic.backend.auth.core;

import com.myclinic.backend.auth.service.userDetail.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContextUtil {

    /** Returns the authenticated user's subject (the user ID used by the JWT). */
    public String getUserId() {
        return getAuthentication().getName();
    }

    /** Returns role names without Spring Security's {@code ROLE_} prefix. */
    public List<String> getRoles() {
        return getAuthentication().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(role -> role.startsWith("ROLE_") ? role.substring("ROLE_".length()) : role)
                .toList();
    }

    /** Returns the tenant ID associated with the authenticated user, or null if absent. */
    public String getTenantId() {
        Object principal = getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails userDetails) {
            return userDetails.getTenantId();
        }
        return null;
    }

    private Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user in the security context");
        }
        return authentication;
    }
}
