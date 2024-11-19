package com.example.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {
    List<OrderProductRequestDto> products;
    String customerName;
    String customerEmail;
    String authorizationCode;
}
