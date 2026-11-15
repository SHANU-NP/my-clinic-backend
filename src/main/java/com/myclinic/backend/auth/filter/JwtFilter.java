package com.myclinic.backend.auth.filter;

import com.myclinic.backend.auth.entity.UserAuth;
import com.myclinic.backend.auth.jwt.JwtUtil;
import com.myclinic.backend.auth.service.repoHandler.UserAuthRepoHandler;
import com.myclinic.backend.auth.service.userDetail.CustomUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

import static com.myclinic.backend.auth.constants.AuthConstants.AUTHORIZATION_HEADER;
import static com.myclinic.backend.auth.constants.AuthConstants.BEARER_PREFIX;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserAuthRepoHandler userAuthRepoHandler;

    public JwtFilter(JwtUtil jwtUtil, UserAuthRepoHandler userAuthRepoHandler) {
        this.jwtUtil = jwtUtil;
        this.userAuthRepoHandler = userAuthRepoHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        String token = null;
        String userId = null;

        if (authorization != null && authorization.startsWith(BEARER_PREFIX)) {
            token = authorization.substring(7);
            try {
                userId = jwtUtil.extractUserId(token);
            } catch (Exception e) {
                // Invalid or expired token — skip authentication, let Security chain decide
            }
        }

        if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserAuth user = userAuthRepoHandler.findByUserId(UUID.fromString(userId)).orElse(null);
            if (user != null) {
                UserDetails userDetails = new CustomUserDetails(user);
                if (jwtUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
