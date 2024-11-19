package com.radov18.borrowing.service;

import com.radov18.borrowing.dto.BookResDto;
import com.radov18.borrowing.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "book-service")
public interface BookService {
    @GetMapping("/api/v1/books/{id}")
    ResponseDto<BookResDto> getBookById(@PathVariable Long id);

    @PostMapping("/api/v1/books/{id}/borrow")
    void borrowBook(@PathVariable Long id);

    @PostMapping("/api/v1/books/{id}/return")
    void returnBook(@PathVariable Long id);
}
