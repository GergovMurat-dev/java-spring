package com.murat.auth;

import com.murat.auth.dto.JwtTokens;
import com.murat.auth.entities.CanAuthorized;
import com.murat.auth.requests.LoginRequest;
import com.murat.auth.requests.RefreshTokenRequest;
import com.murat.auth.requests.RegisterRequest;
import com.murat.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthServiceImpl(UserService userService,
                           AuthenticationManager authenticationManager,
                           JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public JwtTokens register(RegisterRequest data) {

        CanAuthorized user = this.userService
                .findByEmail(data.email())
                .orElseGet(() -> userService.create(data));

        return generateTokens(user);
    }

    @Override
    public JwtTokens login(LoginRequest data) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        data.email(),
                        data.password()
                )
        );

        CanAuthorized user = this.userService
                .findByEmail(data.email())
                .orElseThrow(() -> new IllegalArgumentException("Введены некоррентые значения"));

        return generateTokens(user);
    }

    @Override
    public JwtTokens refreshTokens(RefreshTokenRequest data) {
        String email = jwtService.extractUsername(data.token());
        CanAuthorized user = userService.findByEmail(email).orElseThrow();

        if (jwtService.isTokenValid(data.token(), user)) {
            return generateTokens(user);
        }

        return null;
    }

    private JwtTokens generateTokens(CanAuthorized user) {
        String token = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        return new JwtTokens(token, refreshToken);
    }
}
