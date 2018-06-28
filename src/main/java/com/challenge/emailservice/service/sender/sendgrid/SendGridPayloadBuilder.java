package com.challenge.emailservice.service.sender.sendgrid;

import com.challenge.emailservice.data.Email;
import com.challenge.emailservice.data.EmailAddress;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

public class SendGridPayloadBuilder {

    private ObjectMapper objectMapper;

    public SendGridPayloadBuilder() {
        objectMapper = new ObjectMapper();
    }

    public ObjectNode build(final Email email) {

        ObjectNode root = objectMapper.createObjectNode();
        root.set("personalizations", createPersonalizations(email));
        root.set("from", createEmailAddress(email.getFrom()));
        root.put("subject", email.getSubject());
        root.set("content", createContent(email));

        return root;
    }

    private JsonNode createContent(final Email email) {

        ObjectNode contentNode = objectMapper.createObjectNode();
        contentNode.put("type", TEXT_PLAIN_VALUE);
        contentNode.put("value", email.getContent());

        ArrayNode contentsNode = objectMapper.createArrayNode();
        contentsNode.add(contentNode);

        return contentsNode;
    }

    private JsonNode createPersonalizations(final Email email) {

        ObjectNode personalization = objectMapper.createObjectNode();
        personalization.set("to", createEmailAddresses(email.getTo()));

        if (email.getCc().isPresent()) {
            personalization.set("cc", createEmailAddresses(email.getCc().get()));
        }
        if (email.getBcc().isPresent()) {
            personalization.set("bcc", createEmailAddresses(email.getBcc().get()));
        }

        ArrayNode personalizations = objectMapper.createArrayNode();
        personalizations.add(personalization);
        return personalizations;
    }

    private JsonNode createEmailAddresses(List<EmailAddress> emailAddresses) {

        ArrayNode emailAddressesNode = objectMapper.createArrayNode();

        for (EmailAddress emailAddress : emailAddresses) {
            emailAddressesNode.add(createEmailAddress(emailAddress));
        }

        return emailAddressesNode;
    }

    private JsonNode createEmailAddress(final EmailAddress emailAddress) {
        
        ObjectNode emailAddressNode = objectMapper.createObjectNode();

        if (emailAddress.getName().isPresent()) {
            emailAddressNode.put("name", emailAddress.getName().get());
        }
        emailAddressNode.put("email", emailAddress.getAddress());

        return emailAddressNode;
    }
}
