package com.radov18.notification.services.impl;

import java.util.Properties;

import com.radov18.notification.dto.BorrowingResDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.radov18.notification.dto.BookResDto;
import com.radov18.notification.services.NotificationService;

import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationService.class);

    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;

    @Override
    public void sendBorrowingConfirmation(BorrowingResDto borrowingResDto) {
        JavaMailSenderImpl mailSender = createMailSender();
        String bookTitle = borrowingResDto.getBook().getTitle();
        String email = borrowingResDto.getEmail();
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject("Borrowing confirmation");
            mimeMessageHelper.setText("You have borrowed the book " + bookTitle);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setFrom("librarymail@example.com");
            logger.info("Sending email to {}", email);
            mailSender.send(mimeMessage);
        } catch(Exception e) {
            logger.error("Error sending email", e);
        }
    }

    @Override
    public void sendReturnConfirmation(BorrowingResDto borrowingResDto) {
        JavaMailSenderImpl mailSender = createMailSender();
        String bookTitle = borrowingResDto.getBook().getTitle();
        String email = borrowingResDto.getEmail();
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject("Return confirmation");
            mimeMessageHelper.setText("You have returned the book " + bookTitle);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setFrom("librarymail@exmplae.com");
            logger.info("Sending email to {}", email);
            mailSender.send(mimeMessage);
        } catch(Exception e) {
            logger.error("Error sending email", e);
        }
    }

    private JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername("");
        mailSender.setPassword("");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");

        return mailSender;
    }

}
