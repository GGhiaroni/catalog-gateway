package com.GabrielTiziano.CatalogGateway.model.enums;

import lombok.Getter;

@Getter
public enum Role {
    CUSTOMER("Cliente"),
    SUPPORT("Suporte ao Cliente"),
    MANAGER("Gerente de Loja"),
    ADMIN("Administrador do Sistema");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
