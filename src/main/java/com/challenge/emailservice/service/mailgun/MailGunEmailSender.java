package com.challenge.emailservice.service.mailgun;

import com.challenge.emailservice.data.Email;
import com.challenge.emailservice.service.EmailSender;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64;

public class MailGunEmailSender implements EmailSender {

    private String url;
    private MailGunPayloadBuilder payloadBuilder;
    private HttpHeaders headers;
    private RestTemplate restTemplate;


    public MailGunEmailSender(final String url, final String apiKey) {
        this.url = url;
        payloadBuilder = new MailGunPayloadBuilder();
        headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + new String(encodeBase64(("api:" + apiKey).getBytes())));
        headers.setContentType(MediaType.TEXT_PLAIN);

        restTemplate = new RestTemplate();
    }

    @Override
    public void send(Email email) {

        HttpEntity<String> response = restTemplate.exchange(
                buildUrl(email),
                HttpMethod.POST,
                new HttpEntity<>(headers),
                String.class);

        if (((ResponseEntity<String>) response).getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException();
        }
    }

    private String buildUrl(Email email) {


        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParams(payloadBuilder.build(email));
        return builder.toUriString();
    }


}
