package com.murat.auth;

import com.murat.auth.entities.CanAuthorized;
import com.murat.auth.dto.CreateCanAuthorizedDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserService extends UserDetailsService {
    CanAuthorized create(CreateCanAuthorizedDTO data);

    Optional<CanAuthorized> findByEmail(String email);

    void createDefaultAdmin();
}
