package com.radov18.book.amqp.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.radov18.book.amqp.dto.BorrowingResDto;
import com.radov18.book.amqp.dto.MessageDto;
import com.radov18.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BorrowingEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(BorrowingEventConsumer.class);

    private final BookService bookService;

    @RabbitListener(queues = "book-borrowing-queue")
    public void handleBorrowingEvent(MessageDto<BorrowingResDto> message) {
        logger.info("Handling borrowing event of type {}", message.getType());
        if(message.getType().equals("BORROWING_CREATED")) {
            bookService.borrowBook(message.getData().getBook().getId());
        } else if(message.getType().equals("BORROWING_RETURNED")) {
            bookService.returnBook(message.getData().getBook().getId());
        }
    }
}
