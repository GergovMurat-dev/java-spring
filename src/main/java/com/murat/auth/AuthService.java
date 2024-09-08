package com.murat.auth;

import com.murat.auth.dto.JwtTokens;
import com.murat.auth.requests.LoginRequest;
import com.murat.auth.requests.RefreshTokenRequest;
import com.murat.auth.requests.RegisterRequest;

public interface AuthService {
    JwtTokens register(RegisterRequest data);

    JwtTokens login(LoginRequest data);

    JwtTokens refreshTokens(RefreshTokenRequest data);
}
