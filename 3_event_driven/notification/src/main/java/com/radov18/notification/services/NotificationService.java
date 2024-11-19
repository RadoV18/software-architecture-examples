package com.radov18.notification.services;

import com.radov18.notification.dto.BorrowingResDto;

public interface NotificationService {
    void sendBorrowingConfirmation(BorrowingResDto borrowingResDto);
    void sendReturnConfirmation(BorrowingResDto borrowingResDto);
}
