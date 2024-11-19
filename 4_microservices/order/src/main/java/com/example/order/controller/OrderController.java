package com.example.order.controller;

import com.example.order.dto.OrderRequestDto;
import com.example.order.dto.OrderResponseDto;
import com.example.order.dto.ResponseDto;
import com.example.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    @PostMapping
    public ResponseDto<OrderResponseDto> createOrder(
            @RequestBody OrderRequestDto orderRequestDto
    ) {
        logger.info("POST /api/v1/orders");
        OrderResponseDto result = orderService.createOrder(orderRequestDto);
        return ResponseDto.<OrderResponseDto>builder()
                .data(result)
                .success(true)
                .message("Order created successfully")
                .build();
    }

}
