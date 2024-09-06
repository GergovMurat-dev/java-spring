package com.murat.auth.dto;

import com.murat.auth.contracts.CreateCanAuthorizedEntityDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotNull(message = "Значение поля 'Имя' обязательно")
        String name,

        @Email(message = "Не подходит по формат email")
        String email,

        @NotNull(message = "Значение поля 'Пароль' обязательно")
        String password) implements CreateCanAuthorizedEntityDTO {
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
