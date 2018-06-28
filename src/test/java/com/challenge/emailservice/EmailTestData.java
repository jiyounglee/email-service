package com.challenge.emailservice;

import com.challenge.emailservice.data.Email;
import com.challenge.emailservice.data.EmailAddress;

import java.util.Optional;

import static java.util.Optional.of;
import static org.assertj.core.util.Lists.newArrayList;

public class EmailTestData {

    public static Email createEmail() {

        EmailAddress from = new EmailAddress();
        from.setName(of("FROM"));
        from.setAddress("from@email.com");

        EmailAddress to = new EmailAddress();
        to.setName(Optional.empty());
        to.setAddress("to@email.com");

        Email email = new Email();
        email.setFrom(from);
        email.setTo(newArrayList(to));
        email.setCc(Optional.empty());
        email.setBcc(Optional.empty());
        email.setSubject("Subject");
        email.setContent("Some Content");

        return email;
    }

    public static Email createFullEmail() {

        EmailAddress from = new EmailAddress();
        from.setName(of("FROM"));
        from.setAddress("from@email.com");

        EmailAddress to = new EmailAddress();
        to.setName(Optional.of("TO ONE"));
        to.setAddress("to1@email.com");

        Email email = new Email();
        email.setFrom(from);
        email.setTo(newArrayList(to, new EmailAddress("to2@email.com")));
        email.setCc(of(newArrayList(new EmailAddress("cc@email.com"))));
        email.setBcc(of(newArrayList(new EmailAddress("bcc1@email.com"), new EmailAddress("bcc2@email.com"))));
        email.setSubject("Full Email Some Subject");
        email.setContent("Full Email Some Content");

        return email;
    }


}
