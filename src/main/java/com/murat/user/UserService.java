package com.murat.user;

import com.murat.auth.contracts.CanAuthorizedEntity;
import com.murat.auth.contracts.CreateCanAuthorizedEntityDTO;
import com.murat.auth.contracts.UserServiceContract;
import com.murat.shared.utils.ServiceResult;
import com.murat.user.dto.CreateDTO;
import com.murat.user.enums.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceContract {

    @Value("${environment.adminEmail}")
    private String adminEmail;

    @Value("${environment.adminPassword}")
    private String adminPassword;

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDAO userDAO,
                       @Lazy PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ServiceResult<CanAuthorizedEntity> create(CreateCanAuthorizedEntityDTO data) {
        User user = new User();

        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setRole(Role.USER);

        try {
            this.userDAO.save(user);
            return ServiceResult.success(user);
        } catch (Exception e) {
            return ServiceResult.failure("Произошла ошибка на стороне сервера, повторите попытку позднее");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userDAO
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public ServiceResult<CanAuthorizedEntity> createDefaultAdmin() {
        User admin = userDAO.findByRole(Role.ADMIN);

        if (admin != null) {
            System.out.println("Администратор уже существует в системе");
            return ServiceResult.success(admin);
        }

        admin = new User();

        admin.setName("admin");
        admin.setEmail(adminEmail);
        admin.setPassword(passwordEncoder.encode(adminPassword));
        admin.setRole(Role.ADMIN);

        try {
            userDAO.save(admin);
            System.out.println("Администратор успешно создан");
            return ServiceResult.success(admin);
        } catch (Exception exception) {
            return ServiceResult.failure(exception.getMessage());
        }
    }
}
