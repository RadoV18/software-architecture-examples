package com.example.product.controller;

import com.example.product.dto.ProductRequestDto;
import com.example.product.dto.ProductResponseDto;
import com.example.product.dto.ResponseDto;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @PostMapping
    public ResponseDto<ProductResponseDto> createProduct(
            @RequestBody ProductRequestDto productRequestDto
    ) {
        logger.info("POST /api/v1/products");
        ProductResponseDto result = productService.createProduct(productRequestDto);
        return ResponseDto.<ProductResponseDto>builder()
                .data(result)
                .success(true)
                .message("Product created successfully")
                .build();
    }

    @GetMapping("/{id}")
    public ResponseDto<ProductResponseDto> getProductById(
            @PathVariable Long id
    ) {
        logger.info("GET /api/v1/products/{}", id);
        ProductResponseDto result = productService.getProductById(id);
        return ResponseDto.<ProductResponseDto>builder()
                .data(result)
                .success(true)
                .message("Product retrieved successfully")
                .build();
    }

}
