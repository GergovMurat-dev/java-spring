package com.murat.auth;

import com.murat.auth.entities.CanAuthorized;
import com.murat.auth.requests.RegisterRequest;

public interface AuthService {
    CanAuthorized register(RegisterRequest data);
}
