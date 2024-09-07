package com.murat.auth;

import com.murat.auth.contracts.CanAuthorizedEntity;
import com.murat.auth.dto.RegisterDTO;
import com.murat.shared.utils.ServiceResult;
import org.springframework.http.HttpStatus;
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

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDTO data) {
        ServiceResult<CanAuthorizedEntity> result = this.authService.register(data);

        if (result.isError()) {
            return new ResponseEntity<>(result.getErrorMessage(), HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("");
    }
}
