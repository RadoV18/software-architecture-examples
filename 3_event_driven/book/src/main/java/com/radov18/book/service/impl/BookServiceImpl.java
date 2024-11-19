package com.radov18.book.service.impl;

import com.radov18.book.dto.BookReqDto;
import com.radov18.book.dto.BookResDto;
import com.radov18.book.entity.Book;
import com.radov18.book.repository.BookRepository;
import com.radov18.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;

    @Override
    public BookResDto createBook(BookReqDto bookReqDto) {
        logger.info("Creating book {}", bookReqDto.getTitle());
        Book book = Book.builder()
                .title(bookReqDto.getTitle())
                .author(bookReqDto.getAuthor())
                .description(bookReqDto.getDescription())
                .genre(bookReqDto.getGenre())
                .isbn(bookReqDto.getIsbn())
                .quantity(bookReqDto.getQuantity())
                .build();
        Book savedBook = bookRepository.save(book);
        return mapBookToBookResDto(savedBook);
    }

    @Override
    public BookResDto findBookById(Long id) {
        logger.info("Finding book by id {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return mapBookToBookResDto(book);
    }

    @Override
    public void borrowBook(Long id) {
        logger.info("Decreasing quantity of book with id {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        if(book.getQuantity() == 0) {
            throw new RuntimeException("Book is not available");
        }
        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);
    }

    @Override
    public void returnBook(Long id) {
        logger.info("Increasing quantity of book with id {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        book.setQuantity(book.getQuantity() + 1);
        bookRepository.save(book);
    }

    private BookResDto mapBookToBookResDto(Book book) {
        return BookResDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .description(book.getDescription())
                .genre(book.getGenre())
                .isbn(book.getIsbn())
                .quantity(book.getQuantity())
                .build();
    }
}
