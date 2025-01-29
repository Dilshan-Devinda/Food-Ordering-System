package com.Order.DineDash.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtProvider {
    private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());

    public String generateToken(Authentication auth) {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);

        // Generate the JWT with signing
        String jwt = Jwts.builder()
                .setIssuedAt(new Date()) // Set issue time
                .setExpiration(new Date(new Date().getTime() + 86400000)) // Token valid for 1 day
                .claim("email", auth.getName()) // Custom claim for email
                .claim("authorities", roles) // Custom claim for roles
                .signWith(key, SignatureAlgorithm.HS256) // Sign the token with secret key
                .compact();

        return jwt;
    }
    public String getEmailFromjwtToken(String jwt) {
        jwt = jwt.substring(7);
        Claims claims= Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String email = String.valueOf(claims.get("email"));
        return email;
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String>auths=new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            auths.add(authority.getAuthority());
        }
        return String.join(",", auths);
    }
}
