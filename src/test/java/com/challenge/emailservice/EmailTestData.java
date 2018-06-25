package com.challenge.emailservice;

import com.challenge.emailservice.data.Email;
import com.challenge.emailservice.data.EmailAddress;
import org.assertj.core.util.Lists;

import java.util.Optional;

public class EmailTestData {

    public static Email createEmail() {

        EmailAddress from = new EmailAddress();
        from.setName(Optional.of("FROM"));
        from.setAddress("from@email.com");

        EmailAddress to = new EmailAddress();
        to.setName(Optional.empty());
        to.setAddress("to@email.com");

        Email email = new Email();
        email.setFrom(Lists.newArrayList(from));
        email.setTo(Lists.newArrayList(to));
        email.setCc(Optional.empty());
        email.setBcc(Optional.empty());
        email.setSubject("Subject");
        email.setContent("Some Content");

        return email;
    }

}
