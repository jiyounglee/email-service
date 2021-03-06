package com.challenge.emailservice.data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

public class Email {

    @NotNull
    @Valid
    private EmailAddress from;

    @Valid
    @NotEmpty
    private List<@Valid EmailAddress> to;

    @Valid
    private Optional<List<@Valid EmailAddress>> cc;

    @Valid
    private Optional<List<@Valid EmailAddress>> bcc;

    @NotNull(message = "Subject is required")
    @NotEmpty
    @Size(max = 62)
    private String subject;

    @NotNull(message = "Content is required")
    @NotEmpty
    private String content;

    public Email() {
        cc = Optional.empty();
        bcc = Optional.empty();
    }

    public EmailAddress getFrom() {
        return from;
    }

    public void setFrom(EmailAddress from) {
        this.from = from;
    }

    public List<EmailAddress> getTo() {
        return to;
    }

    public void setTo(List<EmailAddress> to) {
        this.to = to;
    }

    public Optional<List<EmailAddress>> getCc() {
        return cc;
    }

    public void setCc(Optional<List<EmailAddress>> cc) {
        this.cc = cc;
    }

    public Optional<List<EmailAddress>> getBcc() {
        return bcc;
    }

    public void setBcc(Optional<List<EmailAddress>> bcc) {
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

    @Override
    public String toString() {
        return "Email{" +
                "from=" + from +
                ", to=" + to +
                ", cc=" + cc +
                ", bcc=" + bcc +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
