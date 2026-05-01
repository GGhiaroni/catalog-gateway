package com.GabrielTiziano.CatalogGateway.controller;

import com.GabrielTiziano.CatalogGateway.dto.ProductRequest;
import com.GabrielTiziano.CatalogGateway.dto.ProductResponse;
import com.GabrielTiziano.CatalogGateway.model.enums.Category;
import com.GabrielTiziano.CatalogGateway.service.CatalogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/cataloggateway")
@RequiredArgsConstructor
public class ProductController {
    private final CatalogService catalogService;

    @GetMapping("/products")
    public ResponseEntity<Page<ProductResponse>> getProducts(
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) String name,
            Pageable pageable
    ){
        return ResponseEntity.ok(catalogService.list(category, name, pageable));
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id){
        return ResponseEntity.ok(catalogService.findById(id));
    }

    @PostMapping("/products")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogService.createProduct(productRequest));
    }

    @PutMapping("/products/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable String id, @Valid @RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(catalogService.updateProduct(id, productRequest));
    }

    @DeleteMapping("/products/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id){
        catalogService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
