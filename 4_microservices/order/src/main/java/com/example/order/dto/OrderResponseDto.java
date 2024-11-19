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
public class OrderResponseDto {
    private Long id;
    private List<OrderProductResponseDto> products;
    private String customerName;
    private String customerEmail;
    private Double total;
}
