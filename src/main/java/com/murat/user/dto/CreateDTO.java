package com.murat.user.dto;

import com.murat.auth.contracts.CreateCanAuthorizedEntityDTO;
import com.murat.user.enums.Role;

public record CreateDTO(String name,
                        String email,
                        String password,
                        Role role) implements CreateCanAuthorizedEntityDTO {
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
