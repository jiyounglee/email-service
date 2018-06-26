package com.challenge.emailservice.service.sender.sendgrid;

import com.challenge.emailservice.data.Email;
import com.challenge.emailservice.service.sender.EmailSender;
import com.challenge.emailservice.service.sender.EmailSenderStatus;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.ACCEPTED;

public class SendGridEmailSender implements EmailSender {

    private Logger logger = LoggerFactory.getLogger(SendGridEmailSender.class);

    private String url;

    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private SendGridPayloadBuilder payloadBuilder;

    public SendGridEmailSender(final String url, final String apiKey) {

        this.url = url;

        restTemplate = new RestTemplate();

        headers = new HttpHeaders();
        headers.add(AUTHORIZATION, "Bearer " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        payloadBuilder = new SendGridPayloadBuilder();
    }

    @Override
    public EmailSenderStatus send(final Email email) {

        try {
            HttpEntity<ObjectNode> response = restTemplate.exchange(url, POST, new HttpEntity(payloadBuilder.build(email).toString(), headers), ObjectNode.class);
            if (((ResponseEntity<ObjectNode>) response).getStatusCode() == ACCEPTED) {
                return EmailSenderStatus.SUCCEEDED;

            } else {
                logger.error("Failed to Send email", response.toString());
                return EmailSenderStatus.FAILED;

            }
        } catch (Throwable throwable) {
            logger.error("Failed to Send email", throwable);
            return EmailSenderStatus.FAILED;

        }
    }
}
