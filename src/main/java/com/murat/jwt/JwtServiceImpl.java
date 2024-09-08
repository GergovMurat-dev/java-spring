package com.murat.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(userDetails.getUsername());
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(this.getSiginKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 604800000))
                .signWith(this.getSiginKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public Key getSiginKey() {
        byte[] key = Decoders.BASE64.decode("H5x9L0eO/XgXYiK9gF/lCeoJjt79A0WoO3WEt9HhdXo=");
        return Keys.hmacShaKeyFor(key);
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getSiginKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    @Override
    public boolean isTokenExpired(String token) {
        return this.extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
