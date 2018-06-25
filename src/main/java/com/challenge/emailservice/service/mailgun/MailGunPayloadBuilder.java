package com.challenge.emailservice.service.mailgun;

import com.challenge.emailservice.data.Email;
import com.challenge.emailservice.data.EmailAddress;
import com.sun.deploy.net.URLEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

public class MailGunPayloadBuilder {

    MultiValueMap<String, String> build(final Email email) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        buildEmailAddresses(map, "from", email.getFrom());
        buildEmailAddresses(map, "to", email.getTo());

        if (email.getCc().isPresent()) {
            buildEmailAddresses(map, "cc", email.getCc().get());
        }
        if (email.getBcc().isPresent()) {
            buildEmailAddresses(map, "bcc", email.getBcc().get());
        }

        try {
            map.add("subject", URLEncoder.encode(email.getSubject(), "UTF-8"));
            map.add("text", URLEncoder.encode(email.getContent(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return map;
    }

    private void buildEmailAddresses(MultiValueMap<String, String> map, final String type, final List<EmailAddress> emailAddresses) {

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
            return URLEncoder.encode(stringBuilder.toString(), "UTF-8");
        } else {
            return emailAddress.getAddress();
        }
    }
}
