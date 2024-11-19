package com.radov18.notification.amqp.consumer;

import com.radov18.notification.amqp.dto.MessageDto;
import com.radov18.notification.dto.BorrowingResDto;
import com.radov18.notification.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BorrowingEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(BorrowingEventConsumer.class);

    private final NotificationService notificationService;

    @RabbitListener(queues = "notification-borrowing-queue")
    public void consumeBorrowingEvent(MessageDto<BorrowingResDto> message) {
        // sleep for 5 seconds to simulate processing
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Handling borrowing event of type {}", message.getType());
        if(message.getType().equals("BORROWING_CREATED")) {
            notificationService.sendBorrowingConfirmation(message.getData());
        } else if(message.getType().equals("BORROWING_RETURNED")) {
            notificationService.sendReturnConfirmation(message.getData());
        }
    }

}
