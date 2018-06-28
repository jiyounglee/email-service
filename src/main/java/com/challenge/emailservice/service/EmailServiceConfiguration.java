package com.challenge.emailservice.service;

import com.challenge.emailservice.service.sender.EmailSender;
import com.challenge.emailservice.service.sender.mailgun.MailGunEmailSender;
import com.challenge.emailservice.service.sender.sendgrid.SendGridEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class EmailServiceConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public List<EmailSender> emailSenders() {

        MailGunEmailSender mailGunEmailSender = new MailGunEmailSender(
                env.getProperty("email.sender.mailgun.url"),
                env.getProperty("email.sender.mailgun.api.key"));

        SendGridEmailSender sendGridEmailSender = new SendGridEmailSender(
                env.getProperty("email.sender.sendgrid.url"),
                env.getProperty("email.sender.sendgrid.api.key"));

        List<EmailSender> emailSenders = new ArrayList<>();
        emailSenders.add(sendGridEmailSender);
        emailSenders.add(mailGunEmailSender);

        return emailSenders;
    }
}
