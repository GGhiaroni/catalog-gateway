package com.GabrielTiziano.CatalogGateway.model;

import com.GabrielTiziano.CatalogGateway.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;

    @Indexed
    private String name;

    private String description;

    @Indexed(unique = true)
    private String sku;

    @Indexed
    private Category category;

    private BigDecimal price;

    private Integer stock;

    @Builder.Default
    private Map<String, Object> attributes = new HashMap<>();

    @Builder.Default
    private List<String> imageUrls = List.of();

    @Builder.Default
    private Boolean active = true;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
