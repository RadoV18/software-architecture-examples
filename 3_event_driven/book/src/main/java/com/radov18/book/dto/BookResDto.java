package com.radov18.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResDto {
    private Long id;
    private String title;
    private String author;
    private String description;
    private String genre;
    private String isbn;
    private Integer quantity;
}
