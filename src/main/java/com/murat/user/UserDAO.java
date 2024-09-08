package com.murat.user;

import com.murat.auth.entities.CanAuthorized;
import com.murat.user.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {
    Optional<CanAuthorized> findByEmail(String email);

    User findByRole(Role role);
}
