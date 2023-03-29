package com.example.livecodeecommerce.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expire}")
    private int jwtExpiration;
    public String generateToken(String subject){
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .setExpiration(new Date(System.currentTimeMillis()+ jwtExpiration));
        return builder.compact();
    }

    public  boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            throw new RuntimeException("Invalid JWT token...");
        }catch (ExpiredJwtException e){
            throw new RuntimeException("JWT token expired...");
        }catch (UnsupportedJwtException e){
            throw new RuntimeException("JWT token unsupported...");
        }catch (IllegalArgumentException e){
            throw new RuntimeException("JWT token invalid...");
        }catch (SignatureException e){
            throw new RuntimeException("JWT signature not match...");
        }
    }
}
