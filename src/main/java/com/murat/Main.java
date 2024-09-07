package com.murat;

import com.murat.auth.contracts.CanAuthorizedEntity;
import com.murat.auth.contracts.UserServiceContract;
import com.murat.shared.utils.ServiceResult;
import com.murat.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@SpringBootApplication
public class Main implements CommandLineRunner {

    private final UserServiceContract userService;

    public Main(UserServiceContract userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        ServiceResult<CanAuthorizedEntity> createAdminServiceResult = userService.createDefaultAdmin();

        if (createAdminServiceResult.isError()) {
            System.out.println(createAdminServiceResult.getErrorMessage());
        }

        System.out.println("Всё успешно выполнено");
    }
}
