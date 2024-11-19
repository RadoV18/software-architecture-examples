package com.radov18.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResDto {
    private Long id;
    private String title;
    private String author;
    private String description;
    private String genre;
    private String isbn;
    private Integer quantity;
}
