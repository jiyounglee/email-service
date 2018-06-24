package com.challenge.emailservice.data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class Email {

    @Valid
    private List<@Valid EmailAddress> from;

    @Valid
    private List<@Valid EmailAddress> to;

    @Valid
    private List<@Valid EmailAddress> cc;

    @Valid
    private List<@Valid EmailAddress> bcc;

    @NotNull(message = "Subject is required")
    @Size(min = 1)
    private String subject;

    @NotNull(message = "Content is required")
    @Size(min = 1)
    private String content;

    public List<EmailAddress> getFrom() {
        return from;
    }

    public void setFrom(List<EmailAddress> from) {
        this.from = from;
    }

    public List<EmailAddress> getTo() {
        return to;
    }

    public void setTo(List<EmailAddress> to) {
        this.to = to;
    }

    public List<EmailAddress> getCc() {
        return cc;
    }

    public void setCc(List<EmailAddress> cc) {
        this.cc = cc;
    }

    public List<EmailAddress> getBcc() {
        return bcc;
    }

    public void setBcc(List<EmailAddress> bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
