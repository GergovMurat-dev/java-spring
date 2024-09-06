package com.murat.shared.config;

import com.murat.auth.contracts.UserServiceContract;
import com.murat.user.UserDAO;
import com.murat.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserServiceContract getUserServiceContract(UserDAO userDAO) {
        return new UserService(userDAO);
    }
}
