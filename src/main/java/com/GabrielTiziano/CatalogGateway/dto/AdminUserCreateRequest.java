package com.GabrielTiziano.CatalogGateway.dto;

import com.GabrielTiziano.CatalogGateway.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record AdminUserCreateRequest(
        @NotBlank(message = "Nome é um campo obrigatório.")
        String name,

        @NotBlank(message = "E-mail é um campo obrigatório.")
        @Email(message = "Favor inserir um e-mail válido.")
        String email,

        @NotBlank(message = "Senha é um campo obrigatório.")
        @Size(min = 8, message = "A senha deve ter, no mínimo, 8 caracteres.")
        String password,

        @NotEmpty(message = "Pelo menos uma role é obrigatória.")
        Set<Role> roles
) {
}
