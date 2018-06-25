package com.challenge.emailservice.service;

import com.challenge.emailservice.data.Email;
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

        for (EmailSender emailSender : emailSenders) {
            try {
                emailSender.send(email);
                return;
            } catch (Exception e) {
                logger.error("failed to send", e);
            }
        }
        throw new RuntimeException();
    }
}
