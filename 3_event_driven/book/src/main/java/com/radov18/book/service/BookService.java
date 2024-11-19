package com.radov18.book.service;

import com.radov18.book.dto.BookReqDto;
import com.radov18.book.dto.BookResDto;

public interface BookService {
    BookResDto createBook(BookReqDto bookReqDto);
    BookResDto findBookById(Long id);
    void borrowBook(Long id);
    void returnBook(Long id);
}
