package com.GabrielTiziano.CatalogGateway.dto;

import com.GabrielTiziano.CatalogGateway.model.enums.Category;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record ProductRequest(
        @NotBlank(message = "Nome é um campo obrigatório.")
        String name,
        @NotBlank(message = "Descrição é um campo obrigatório")
        @Size(min = 10, message = "A descrição deve ter, no mínimo, 10 caracteres.")
        String description,
        @NotNull(message = "Categoria é um campo obrigatório.")
        Category category,
        @NotNull(message = "Preço é um campo obrigatório.")
        @Positive(message = "Preço precisa ser um valor maior que 0.")
        BigDecimal price,
        @Min(value = 0, message = "O valor mínimo do Estoque é zero.")
        @NotNull(message = "Estoque é um campo obrigatório.")
        Integer stock,
        @NotNull(message = "Atributos é um campo obrigatório.")
        Map<String, Object> attributes,
        @NotNull(message = "URL das imagens é um campo obrigatório.")
        List<String> imageUrls
) {
}
