package com.radov18.book.controller;

import com.radov18.book.dto.BookReqDto;
import com.radov18.book.dto.BookResDto;
import com.radov18.book.dto.ResponseDto;
import com.radov18.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ResponseDto<BookResDto>> createBook(
            @RequestBody BookReqDto bookReqDto
    ) {
        logger.info("POST /books");
        BookResDto book = bookService.createBook(bookReqDto);
        return ResponseEntity.ok(
                ResponseDto.<BookResDto>builder()
                        .data(book)
                        .success(true)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<BookResDto>> findBookById(
            @PathVariable Long id
    ) {
        logger.info("GET /books/{}", id);
        BookResDto book = bookService.findBookById(id);
        return ResponseEntity.ok(
                ResponseDto.<BookResDto>builder()
                        .data(book)
                        .success(true)
                        .build()
        );
    }

    @PostMapping("/{id}/borrow")
    public ResponseEntity<ResponseDto<Void>> borrowBook(
            @PathVariable Long id
    ) {
        logger.info("POST /books/{}/borrow", id);
        bookService.borrowBook(id);
        return ResponseEntity.ok(
                ResponseDto.<Void>builder()
                        .success(true)
                        .build()
        );
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<ResponseDto<Void>> returnBook(
            @PathVariable Long id
    ) {
        logger.info("POST /books/{}/return", id);
        bookService.returnBook(id);
        return ResponseEntity.ok(
                ResponseDto.<Void>builder()
                        .success(true)
                        .build()
        );
    }

}
