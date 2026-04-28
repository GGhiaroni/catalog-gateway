package com.GabrielTiziano.CatalogGateway.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "E-mail é um campo obrigatório.")
        String email,

        @NotBlank(message = "Senha é um campo obrigatório.")
        String password
) {
}
