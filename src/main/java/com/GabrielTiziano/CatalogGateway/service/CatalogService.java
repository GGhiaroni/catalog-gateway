package com.GabrielTiziano.CatalogGateway.service;

import com.GabrielTiziano.CatalogGateway.dto.ProductRequest;
import com.GabrielTiziano.CatalogGateway.dto.ProductResponse;
import com.GabrielTiziano.CatalogGateway.mapper.ProductMapper;
import com.GabrielTiziano.CatalogGateway.model.Product;
import com.GabrielTiziano.CatalogGateway.model.enums.Category;
import com.GabrielTiziano.CatalogGateway.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CatalogService {
    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        if (productRepository.existsBySku(productRequest.sku())) {
            // TODO M3: trocar por SkuAlreadyExistsException
            throw new RuntimeException("SKU já cadastrado no catálogo.");
        }

        return ProductMapper.toResponse(productRepository.save(ProductMapper.toEntity(productRequest)));
    }

    public ProductResponse findById(String id) {
        Product existingProduct = productRepository.findByIdAndActiveTrue(id)
                // TODO M3: trocar por ProductNotFoundException
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        return ProductMapper.toResponse(existingProduct);
    }

    public Page<ProductResponse> list(Category category, String name, Pageable pageable){
        boolean hasCategory = category != null;
        boolean hasName = StringUtils.hasText(name);

        Page<Product> result;

        if(hasCategory && hasName){
            result = productRepository.findByCategoryAndNameContainingIgnoreCaseAndActiveTrue(category, name, pageable);
        } else if (hasCategory) {
            result = productRepository.findByCategoryAndActiveTrue(category, pageable);
        } else if(hasName){
            result = productRepository.findByNameContainingIgnoreCaseAndActiveTrue(name, pageable);
        } else {
            result = productRepository.findByActiveTrue(pageable);
        }

        return result.map(ProductMapper::toResponse);
    }

    public ProductResponse updateProduct(String id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id)
                // TODO M3: trocar por ProductNotFoundException
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        ProductMapper.updateEntity(productRequest, existingProduct);

        Product savedProduct = productRepository.save(existingProduct);

        return ProductMapper.toResponse(savedProduct);
    }


    public void deleteById(String id) {
        Product existingProduct = productRepository.findById(id)
                // TODO M3: trocar por ProductNotFoundException
                .orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        existingProduct.setActive(false);

        productRepository.save(existingProduct);
    }
}
