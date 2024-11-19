package com.radov18.book.amqp.dto;

import com.radov18.book.dto.BookResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowingResDto {
    private Long id;
    private BookResDto book;
    private String email;
    private Date borrowingDate;
    private Date returnDate;
    private boolean returned;
}
