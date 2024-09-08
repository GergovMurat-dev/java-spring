package com.murat.auth;

import com.murat.auth.entities.CanAuthorized;
import com.murat.auth.requests.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }

    public CanAuthorized register(RegisterRequest data) {

        CanAuthorized user = this.userService
                .findByEmail(data.email())
                .orElseGet(() -> userService.create(data));

        return user;
    }
}
