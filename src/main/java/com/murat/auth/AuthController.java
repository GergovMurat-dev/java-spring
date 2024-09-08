package com.murat.auth;

import com.murat.auth.entities.CanAuthorized;
import com.murat.auth.requests.RegisterRequest;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<CanAuthorized> register(@Valid @RequestBody RegisterRequest data) {
        CanAuthorized user = this.authService.register(data);

        return ResponseEntity.ok(user);
    }
}
