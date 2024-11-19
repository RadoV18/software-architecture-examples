package com.example.order.service.impl;

import com.example.order.dto.*;
import com.example.order.service.OrderService;
import com.example.order.service.ProductService;
import com.example.order.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    private final ProductService productService;
    private final UserService userService;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        // get product and validate quantity
        List<OrderProductRequestDto> products = orderRequestDto.getProducts();
        for (OrderProductRequestDto product : products) {
            ResponseDto<ProductResponseDto> productResponse = productService.getProductById(product.getProductId());
            logger.info("Product {} {} retrieved successfully", productResponse.getData().getId(), productResponse.getData().getName());
            if(product.getQuantity() > productResponse.getData().getStock()) {
                throw new IllegalArgumentException("Product " + productResponse.getData().getName() + " stock is not enough");
            }
        }
        // validate authorization code
        ResponseDto<Boolean> validCode = userService.isValidAuthorizationCode(orderRequestDto.getAuthorizationCode());
        if (!validCode.getData()) {
            throw new IllegalArgumentException("Invalid authorization code");
        }
        return null;
    }
}
