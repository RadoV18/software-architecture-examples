package com.radov18.borrowing.service.impl;

import com.radov18.borrowing.amqp.dto.MessageDto;
import com.radov18.borrowing.amqp.producer.BorrowingEventProducer;
import com.radov18.borrowing.dto.BookResDto;
import com.radov18.borrowing.dto.BorrowingReqDto;
import com.radov18.borrowing.dto.BorrowingResDto;
import com.radov18.borrowing.entity.Borrowing;
import com.radov18.borrowing.repository.BorrowingRepository;
import com.radov18.borrowing.service.BookService;
import com.radov18.borrowing.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private static final Logger logger = LoggerFactory.getLogger(BorrowingServiceImpl.class);

    private final BookService bookService;
    private final BorrowingRepository borrowingRepository;
    private final BorrowingEventProducer borrowingEventProducer;

    @Override
    public BorrowingResDto borrowBook(BorrowingReqDto borrowingReqDto) {
        logger.info("Borrowing book {}...", borrowingReqDto.getBookId());
        BookResDto book;
        try {
            book = bookService.getBookById(borrowingReqDto.getBookId()).getData();
            if(book.getQuantity() == 0) {
                throw new RuntimeException("Book is not available");
            }
        } catch (Exception e) {
            logger.error("Error borrowing book", e);
            throw new RuntimeException("Error borrowing book");
        }
        Borrowing borrowing = Borrowing.builder()
                .bookId(borrowingReqDto.getBookId())
                .email(borrowingReqDto.getEmail())
                .borrowingDate(borrowingReqDto.getBorrowingDate())
                .returnDate(borrowingReqDto.getReturnDate())
                .returned(false)
                .build();
        Borrowing savedBorrowing = borrowingRepository.save(borrowing);

        BorrowingResDto result = BorrowingResDto.builder()
                .id(savedBorrowing.getId())
                .book(book)
                .email(savedBorrowing.getEmail())
                .borrowingDate(savedBorrowing.getBorrowingDate())
                .returnDate(savedBorrowing.getReturnDate())
                .returned(savedBorrowing.isReturned())
                .build();

        MessageDto<BorrowingResDto> message = MessageDto.<BorrowingResDto>builder()
                .type("BORROWING_CREATED")
                .data(result)
                .build();
        borrowingEventProducer.sendMessage("borrowing-exchange", "created.borrowing", message);

        return result;
    }

    @Override
    public void returnBook(Long borrowingId) {
        logger.info("Returning book with id {}", borrowingId);
        Borrowing borrowing = borrowingRepository.findById(borrowingId)
                .orElseThrow(() -> new RuntimeException("Borrowing not found"));
        if(borrowing.isReturned()) {
            throw new RuntimeException("Book already returned");
        }
        borrowing.setReturned(true);
        borrowingRepository.save(borrowing);

        MessageDto<BorrowingResDto> message = MessageDto.<BorrowingResDto>builder()
                .type("BORROWING_RETURNED")
                .data(mapBorrowingToBorrowingResDto(borrowing))
                .build();
        borrowingEventProducer.sendMessage("borrowing-exchange", "returned.borrowing", message);
    }

    @Override
    public BorrowingResDto getBorrowingById(Long id) {
        Borrowing borrowing = borrowingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Borrowing not found"));
        BookResDto book = bookService.getBookById(borrowing.getBookId()).getData();
        book.setQuantity(1);
        return mapBorrowingToBorrowingResDto(borrowing);
    }

    private BorrowingResDto mapBorrowingToBorrowingResDto(Borrowing borrowing) {
        BookResDto book = bookService.getBookById(borrowing.getBookId()).getData();
        return BorrowingResDto.builder()
                .id(borrowing.getId())
                .book(book)
                .email(borrowing.getEmail())
                .borrowingDate(borrowing.getBorrowingDate())
                .returnDate(borrowing.getReturnDate())
                .returned(borrowing.isReturned())
                .build();
    }
}
