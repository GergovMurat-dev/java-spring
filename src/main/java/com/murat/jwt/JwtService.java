package com.murat.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateToken(UserDetails userDetails);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    Key getSiginKey();

    Claims extractAllClaims(String token);

    boolean isTokenExpired(String token);
}
