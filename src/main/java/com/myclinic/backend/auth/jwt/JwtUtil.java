package com.myclinic.backend.auth.jwt;

import com.myclinic.backend.auth.entity.UserAuth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.myclinic.backend.auth.constants.AuthConstants.ROLES_CLAIM;
import static com.myclinic.backend.auth.constants.AuthConstants.TENANT_ID_CLAIM;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration:600000}")
    private long expiration;

    public String generateToken(UserAuth user) {
        Map<String, Object> claims = new HashMap<>();
        List<String> roles = Arrays.stream(user.getRole().split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        claims.put(ROLES_CLAIM, roles);
        if (user.getTenantId() != null) {
            claims.put(TENANT_ID_CLAIM, user.getTenantId());
        }
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(String.valueOf(user.getUserId()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get(ROLES_CLAIM, List.class);
    }

    public String extractTenantId(String token) {
        Claims claims = extractAllClaims(token);
        return claims.get(TENANT_ID_CLAIM, String.class);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userId = extractUserId(token);
        return (userId.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build().parseClaimsJws(token).getBody();
    }
}
