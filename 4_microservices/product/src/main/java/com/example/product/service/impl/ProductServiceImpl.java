package com.example.product.service.impl;

import com.example.product.dto.ProductRequestDto;
import com.example.product.dto.ProductResponseDto;
import com.example.product.exception.NotFoundException;
import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productResponseDto) {
        // TODO: verify authorization code against user service
        Long createdBy = 1L;

        Product product = new Product();
        product.setName(productResponseDto.getName());
        product.setDescription(productResponseDto.getDescription());
        product.setPrice(productResponseDto.getPrice());
        product.setStock(productResponseDto.getStock());
        product.setCreatedBy(createdBy);

        Product savedProduct = productRepository.save(product);
        return toProductResponseDto(savedProduct);
    }

    @Override
    public ProductResponseDto getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Product not found");
        }
        return toProductResponseDto(productOptional.get());
    }

    public ProductResponseDto toProductResponseDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
