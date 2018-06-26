package com.challenge.emailservice.service.sender.sendgrid;

import com.challenge.emailservice.data.Email;
import com.challenge.emailservice.data.EmailAddress;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class SendGridPayloadBuilder {

    public ObjectNode build(Email email) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode root = objectMapper.createObjectNode();
        root.set("personalizations", createPersonalizations(objectMapper, email));
        root.set("from", createEmailAddress(objectMapper, email.getFrom()));
        root.put("subject", email.getSubject());
        root.set("content", createContent(objectMapper, email));

        ArrayNode personalizationsValue = objectMapper.createArrayNode();

        ObjectNode personalizationsNode = objectMapper.createObjectNode();
        personalizationsNode.set("personalizations", personalizationsValue);
        return root;
    }

    private JsonNode createContent(ObjectMapper objectMapper, Email email) {
        ArrayNode contentsNode = objectMapper.createArrayNode();
        ObjectNode contentNode = objectMapper.createObjectNode();
        contentNode.put("type", "text/plain");
        contentNode.put("value", email.getContent());
        contentsNode.add(contentNode);
        return contentsNode;
    }

    private JsonNode createPersonalizations(ObjectMapper objectMapper, Email email) {
        ArrayNode recipients = objectMapper.createArrayNode();

        ObjectNode to = objectMapper.createObjectNode();
        to.set("to", createEmailAddresses(objectMapper, email.getTo()));
        recipients.add(to);

        if (email.getCc().isPresent()) {
            ObjectNode cc = objectMapper.createObjectNode();
            cc.set("cc", createEmailAddresses(objectMapper, email.getCc().get()));
            recipients.add(cc);
        }
        if (email.getBcc().isPresent()) {
            ObjectNode bcc = objectMapper.createObjectNode();
            bcc.set("bcc", createEmailAddresses(objectMapper, email.getBcc().get()));
            recipients.add(bcc);
        }
        return recipients;
    }

    private JsonNode createEmailAddresses(ObjectMapper objectMapper, List<EmailAddress> emailAddresses) {
        ArrayNode emailAddressesNode = objectMapper.createArrayNode();
        for (EmailAddress emailAddress : emailAddresses) {
            emailAddressesNode.add(createEmailAddress(objectMapper, emailAddress));
        }

        return emailAddressesNode;
    }

    private JsonNode createEmailAddress(ObjectMapper objectMapper, EmailAddress emailAddress) {
        ObjectNode emailAddressNode = objectMapper.createObjectNode();
        if (emailAddress.getName().isPresent()) {
            emailAddressNode.put("name", emailAddress.getName().get());
        }
        emailAddressNode.put("email", emailAddress.getAddress());
        return emailAddressNode;
    }
}
