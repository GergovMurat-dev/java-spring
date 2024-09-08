package com.murat.auth.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotNull(message = "Значение поля 'Email' обязательно")
        @Email(message = "Значение должно быть email")
        String email,
        @NotNull(message = "Значение поля 'Пароль' обязательно")
        String password) {
}
