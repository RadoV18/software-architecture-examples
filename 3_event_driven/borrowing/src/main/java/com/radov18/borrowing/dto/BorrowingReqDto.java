package com.radov18.borrowing.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowingReqDto {
    private Long bookId;
    private String email;
    private Date borrowingDate;
    private Date returnDate;
}
