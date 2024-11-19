package com.radov18.borrowing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.radov18.borrowing.dto.BorrowingReqDto;
import com.radov18.borrowing.dto.BorrowingResDto;
import com.radov18.borrowing.dto.ResponseDto;
import com.radov18.borrowing.service.BorrowingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/borrowings")
@RequiredArgsConstructor
public class BorrowingController {

    private static final Logger logger = LoggerFactory.getLogger(BorrowingController.class);

    private final BorrowingService borrowingService;

    @PostMapping
    public ResponseEntity<ResponseDto<BorrowingResDto>> createBorrowing(
            @RequestBody BorrowingReqDto borrowingReqDto
    ) {
        logger.info("POST /borrowings");
        BorrowingResDto borrowing = borrowingService.borrowBook(borrowingReqDto);
        return ResponseEntity.ok(
                ResponseDto.<BorrowingResDto>builder()
                        .data(borrowing)
                        .success(true)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<BorrowingResDto>> findBorrowingById(
            @PathVariable Long id
    ) {
        logger.info("GET /borrowings/{}", id);
        BorrowingResDto borrowing = borrowingService.getBorrowingById(id);
        return ResponseEntity.ok(
                ResponseDto.<BorrowingResDto>builder()
                        .data(borrowing)
                        .success(true)
                        .build()
        );
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<ResponseDto<Void>> returnBook(
            @PathVariable Long id
    ) {
        logger.info("POST /borrowings/{}/return", id);
        borrowingService.returnBook(id);
        return ResponseEntity.ok(
                ResponseDto.<Void>builder()
                        .success(true)
                        .build()
        );
    }

}
