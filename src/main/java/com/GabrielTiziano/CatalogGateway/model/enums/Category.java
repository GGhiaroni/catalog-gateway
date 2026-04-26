package com.GabrielTiziano.CatalogGateway.model.enums;

import lombok.Getter;

@Getter
public enum Category {

    ELECTRONICS("Eletrônicos e Tecnologia"),
    HOME_APPLIANCES("Eletrodomésticos"),
    CLOTHING("Moda e Vestuário"),
    SPORTS("Esportes e Lazer"),
    BOOKS("Livros e Papelaria"),
    BEAUTY("Beleza e Cuidado Pessoal"),
    FURNITURE("Móveis e Decoração"),
    GROCERY("Alimentos e Bebidas"),
    TOYS("Brinquedos e Jogos");

    private final String description;

    Category(String description) {
        this.description = description;
    }
}

