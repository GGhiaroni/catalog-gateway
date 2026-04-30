package com.GabrielTiziano.CatalogGateway.repository;

import com.GabrielTiziano.CatalogGateway.model.Product;
import com.GabrielTiziano.CatalogGateway.model.enums.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Page<Product> findByCategoryAndActiveTrue(Category category, Pageable pageable);
    Page<Product> findByNameContainingIgnoreCaseAndActiveTrue(String name, Pageable pageable);
    Page<Product> findByCategoryAndNameContainingIgnoreCaseAndActiveTrue(Category category, String name, Pageable pageable);
    Page<Product> findByActiveTrue(Pageable pageable);
    boolean existsBySku(String sku);
    Optional<Product> findBySku(String sku);
}
