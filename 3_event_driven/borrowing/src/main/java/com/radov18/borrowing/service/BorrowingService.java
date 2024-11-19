package com.radov18.borrowing.service;

import com.radov18.borrowing.dto.BorrowingReqDto;
import com.radov18.borrowing.dto.BorrowingResDto;

public interface BorrowingService {
    BorrowingResDto borrowBook(BorrowingReqDto borrowingReqDto);
    void returnBook(Long borrowingId);

    BorrowingResDto getBorrowingById(Long id);
}
