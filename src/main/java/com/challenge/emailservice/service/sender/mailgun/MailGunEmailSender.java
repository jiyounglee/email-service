package com.challenge.emailservice.service.sender.mailgun;

import com.challenge.emailservice.data.Email;
import com.challenge.emailservice.service.sender.EmailSender;
import com.challenge.emailservice.service.sender.EmailSenderStatus;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;

import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpStatus.OK;

public class MailGunEmailSender implements EmailSender {

    private Logger logger = LoggerFactory.getLogger(MailGunEmailSender.class);

    private String url;

    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private MailGunPayloadBuilder payloadBuilder;

    public MailGunEmailSender(final String url, final String apiKey) {

        this.url = url;

        restTemplate = new RestTemplate();

        headers = new HttpHeaders();
        headers.add(AUTHORIZATION, "Basic " + new String(encodeBase64(("api:" + apiKey).getBytes())));

        payloadBuilder = new MailGunPayloadBuilder();
    }

    @Override
    public EmailSenderStatus send(final Email email) {

        try {
            HttpEntity<ObjectNode> response = restTemplate.exchange(buildUrl(email), POST, new HttpEntity<>(headers), ObjectNode.class);

            if (((ResponseEntity<ObjectNode>) response).getStatusCode() == OK) {
                return EmailSenderStatus.SUCCEEDED;

            } else {
                logger.error("Failed to Send email {}", response.toString());
                return EmailSenderStatus.FAILED;

            }
        } catch (Throwable throwable) {
            logger.error("Failed to Send email", throwable);
            if (throwable instanceof HttpClientErrorException) {
                String error = ((HttpClientErrorException) throwable).getResponseBodyAsString();
                logger.error(error);
            }

            return EmailSenderStatus.FAILED;

        }
    }

    private String buildUrl(final Email email) throws UnsupportedEncodingException {
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        builder.queryParams(payloadBuilder.build(email));

        return builder.build(false).toUriString();
    }


}
