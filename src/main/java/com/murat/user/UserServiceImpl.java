package com.murat.user;

import com.murat.auth.entities.CanAuthorized;
import com.murat.auth.dto.CreateCanAuthorizedDTO;
import com.murat.user.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Value("${environment.adminEmail}")
    private String adminEmail;

    @Value("${environment.adminPassword}")
    private String adminPassword;

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO,
                           @Lazy PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public CanAuthorized create(CreateCanAuthorizedDTO data) {
        User user = new User();

        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setRole(Role.USER);

        this.userDAO.save(user);

        return user;
    }

    @Override
    public Optional<CanAuthorized> findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userDAO
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public void createDefaultAdmin() {
        User admin = userDAO.findByRole(Role.ADMIN);

        if (admin != null) {
            return;
        }

        admin = new User();

        admin.setName("admin");
        admin.setEmail(adminEmail);
        admin.setPassword(passwordEncoder.encode(adminPassword));
        admin.setRole(Role.ADMIN);

        userDAO.save(admin);
    }
}
