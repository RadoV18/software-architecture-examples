package com.example.order.service;

import com.example.order.dto.OrderRequestDto;
import com.example.order.dto.OrderResponseDto;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);
}
