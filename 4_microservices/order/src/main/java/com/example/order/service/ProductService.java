package com.example.order.service;

import com.example.order.dto.ProductResponseDto;
import com.example.order.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service")
public interface ProductService {
    @GetMapping("/api/v1/products/{id}")
    ResponseDto<ProductResponseDto> getProductById(@PathVariable Long id);
}
