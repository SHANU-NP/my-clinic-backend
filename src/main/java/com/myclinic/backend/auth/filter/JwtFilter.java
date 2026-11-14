package com.myclinic.backend.auth.filter;

import com.myclinic.backend.auth.jwt.JwtUtil;
import com.myclinic.backend.auth.service.userDetail.CustomerUserDetailService;
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

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomerUserDetailService customerUserDetailService;

    public JwtFilter(JwtUtil jwtUtil, CustomerUserDetailService customerUserDetailService) {
        this.jwtUtil = jwtUtil;
        this.customerUserDetailService = customerUserDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        String token = null;
        String username = null;
        try{
            if (authorization != null && authorization.startsWith("Bearer ")){
                token = authorization.substring(7);
                username = jwtUtil.extractUserName(token);
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = customerUserDetailService.loadUserByUsername(username);
                boolean isTokenValid = jwtUtil.validateToken(token, userDetails);
                if (isTokenValid){
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }else {
                    throw new RuntimeException("invalid token");
                }
            }
            filterChain.doFilter(request,response);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }


    }
}
