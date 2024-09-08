package com.murat.auth.requests;

import com.murat.auth.dto.CreateCanAuthorizedDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(
        @NotNull(message = "Значение поля 'Имя' обязательно")
        String name,

        @Email(message = "Не подходит по формат email")
        String email,

        @NotNull(message = "Значение поля 'Пароль' обязательно")
        String password) implements CreateCanAuthorizedDTO {
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
