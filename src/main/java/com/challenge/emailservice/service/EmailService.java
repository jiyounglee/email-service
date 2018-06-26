package com.challenge.emailservice.service;

import com.challenge.emailservice.data.Email;
import com.challenge.emailservice.service.sender.EmailSender;
import com.challenge.emailservice.service.sender.EmailSenderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService {

    private Logger logger = LoggerFactory.getLogger(EmailService.class);

    private List<EmailSender> emailSenders;

    @Autowired
    public EmailService(final List<EmailSender> emailSenders) {
        this.emailSenders = emailSenders;
    }

    public void sendEmail(final Email email) {

        logger.info("Sending Email");

        for (EmailSender emailSender : emailSenders) {

            EmailSenderStatus status = emailSender.send(email);
            if (status == EmailSenderStatus.SUCCEEDED) {

                logger.info("Sent Email with sender {}", emailSender.getClass().getSimpleName());
                return;
            }
        }
    }
}
