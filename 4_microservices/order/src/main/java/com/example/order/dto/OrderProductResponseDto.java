package com.example.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductResponseDto {
    private Long productId;
    private String productName;
    private Long quantity;
    private Double price;
    private Double subtotal;
}
