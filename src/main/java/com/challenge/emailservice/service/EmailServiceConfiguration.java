package com.challenge.emailservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class EmailServiceConfiguration {

    @Autowired
    Environment env;

//    @Bean
//    public EmailService emailService() {
//        SendGridEmailSender sendGridEmailSender = new SendGridEmailSender(
//                env.getProperty("email.sender.sendgrid.url"),
//                env.getProperty("email.sender.sendgrid.api.key"));
//
//        List<EmailSender> emailSenders = new ArrayList<>();
//        emailSenders.add(sendGridEmailSender);
//
//        return new EmailService(emailSenders);
//    }

    @Bean
    public List<EmailSender> emailSenders() {
        SendGridEmailSender sendGridEmailSender = new SendGridEmailSender(
                env.getProperty("email.sender.sendgrid.url"),
                env.getProperty("email.sender.sendgrid.api.key"));

        List<EmailSender> emailSenders = new ArrayList<>();
        emailSenders.add(sendGridEmailSender);

        return emailSenders;
    }
}
