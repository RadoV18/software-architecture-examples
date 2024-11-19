package com.example.product.service;

import com.example.product.dto.ProductRequestDto;
import com.example.product.dto.ProductResponseDto;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto productResponseDto);

    ProductResponseDto getProductById(Long id);
}
