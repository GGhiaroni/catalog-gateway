package com.GabrielTiziano.CatalogGateway.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
        @NotBlank(message = "Nome é um campo obrigatório.")
        String name,

        @NotBlank(message = "E-mail é um campo obrigatório.")
        @Email(message = "Favor inserir um e-mail válido")
        String email,

        @NotBlank(message = "Senha é um campo obrigatório.")
        @Size(min=8, message = "A senha deve ter, no mínimo, 8 caracteres.")
        String password
) {
}
