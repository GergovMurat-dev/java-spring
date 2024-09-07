package com.murat.shared.config;

import com.murat.auth.contracts.UserServiceContract;
import com.murat.jwt.JwtService;
import com.murat.shared.contracts.JwtServiceContract;
import com.murat.user.UserDAO;
import com.murat.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public UserServiceContract getUserServiceContract(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        return new UserService(userDAO, passwordEncoder);
    }

    @Bean
    public JwtServiceContract getJwtServiceContract() {
        return new JwtService();
    }
}
