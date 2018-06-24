package com.challenge.emailservice;

import com.challenge.emailservice.data.Email;
import com.challenge.emailservice.data.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailServiceController {

    @PostMapping("/email")
    public @ResponseBody
    Response sendEmail(final Email email) {
        return new Response("succeeded", "sent email");
    }
}
