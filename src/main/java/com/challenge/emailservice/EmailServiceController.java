package com.challenge.emailservice;

import com.challenge.emailservice.data.Email;
import com.challenge.emailservice.data.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailServiceController {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceController.class);

    @PostMapping("/email")
    public @ResponseBody
    Response sendEmail(@Valid @RequestBody final Email email) {
        logger.debug("Received request to send email");
        return new Response("succeeded", "sent email");
    }
}
