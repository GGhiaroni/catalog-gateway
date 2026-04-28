package com.GabrielTiziano.CatalogGateway.dto;

import com.GabrielTiziano.CatalogGateway.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;

@Builder
public record ProductResponse(
        String id,
        String name,
        String description,
        Category category,
        BigDecimal price,
        Integer stock,
        Map<String, Object> attributes,
        List<String> imageUrls,
        boolean active,
        Instant createdAt,
        Instant updatedAt
) {
}
