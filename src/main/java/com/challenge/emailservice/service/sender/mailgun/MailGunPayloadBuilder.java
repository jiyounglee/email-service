package com.challenge.emailservice.service.sender.mailgun;

import com.challenge.emailservice.data.Email;
import com.challenge.emailservice.data.EmailAddress;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class MailGunPayloadBuilder {

    MultiValueMap<String, String> build(final Email email) throws UnsupportedEncodingException {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("from", buildEmailAddress(email.getFrom()));

        buildEmailAddresses(map, "to", email.getTo());
        if (email.getCc().isPresent()) {
            buildEmailAddresses(map, "cc", email.getCc().get());
        }
        if (email.getBcc().isPresent()) {
            buildEmailAddresses(map, "bcc", email.getBcc().get());
        }

        map.add("subject", URLEncoder.encode(email.getSubject(), "UTF-8"));
        map.add("text", URLEncoder.encode(email.getContent(), "UTF-8"));

        return map;
    }

    private void buildEmailAddresses(MultiValueMap<String, String> map, final String type,
                                     final List<EmailAddress> emailAddresses) {

        for (EmailAddress emailAddress : emailAddresses) {
            try {
                map.add(type, buildEmailAddress(emailAddress));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    private String buildEmailAddress(final EmailAddress emailAddress) throws UnsupportedEncodingException {

        if (emailAddress.getName().isPresent()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(emailAddress.getName().get());
            stringBuilder.append(" ");
            stringBuilder.append("<");
            stringBuilder.append(emailAddress.getAddress());
            stringBuilder.append(">");
            return stringBuilder.toString();
        } else {
            return emailAddress.getAddress();
        }
    }
}
