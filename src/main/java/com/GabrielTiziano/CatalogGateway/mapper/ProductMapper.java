package com.GabrielTiziano.CatalogGateway.mapper;

import com.GabrielTiziano.CatalogGateway.dto.ProductRequest;
import com.GabrielTiziano.CatalogGateway.dto.ProductResponse;
import com.GabrielTiziano.CatalogGateway.model.Product;

public class ProductMapper {
    private ProductMapper() {
    }

    public static Product toEntity(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .sku(productRequest.sku())
                .category(productRequest.category())
                .price(productRequest.price())
                .imageUrls(productRequest.imageUrls())
                .attributes(productRequest.attributes())
                .stock(productRequest.stock())
                .build();
    }

    public static void updateEntity(ProductRequest productRequest, Product product) {
                product.setName(productRequest.name());
                product.setDescription(productRequest.description());
                product.setCategory(productRequest.category());
                product.setPrice(productRequest.price());
                product.setImageUrls(productRequest.imageUrls());
                product.setAttributes(productRequest.attributes());
                product.setStock(productRequest.stock());
    }

    public static ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .sku(product.getSku())
                .category(product.getCategory())
                .price(product.getPrice())
                .imageUrls(product.getImageUrls())
                .attributes(product.getAttributes())
                .stock(product.getStock())
                .active(product.getActive())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
