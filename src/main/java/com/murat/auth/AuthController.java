package com.murat.auth;

import com.murat.auth.dto.JwtTokens;
import com.murat.auth.requests.LoginRequest;
import com.murat.auth.requests.RefreshTokenRequest;
import com.murat.auth.requests.RegisterRequest;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<JwtTokens> register(@Valid @RequestBody RegisterRequest data) {
        return ResponseEntity.ok(authService.register(data));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokens> login(@Valid @RequestBody LoginRequest data) {
        return ResponseEntity.ok(authService.login(data));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtTokens> refreshToken(@Valid @RequestBody RefreshTokenRequest data) {
        return ResponseEntity.ok(authService.refreshTokens(data));
    }
}
