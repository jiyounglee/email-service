package com.challenge.emailservice.data;

import java.util.List;

public class Email {

    private List<EmailAddress> tos;
    private List<EmailAddress> froms;
    private List<EmailAddress> ccs;
    private List<EmailAddress> bccs;
    private String subject;
    private String content;

    public List<EmailAddress> getTos() {
        return tos;
    }

    public void setTos(List<EmailAddress> tos) {
        this.tos = tos;
    }

    public List<EmailAddress> getFroms() {
        return froms;
    }

    public void setFroms(List<EmailAddress> froms) {
        this.froms = froms;
    }

    public List<EmailAddress> getCcs() {
        return ccs;
    }

    public void setCcs(List<EmailAddress> ccs) {
        this.ccs = ccs;
    }

    public List<EmailAddress> getBccs() {
        return bccs;
    }

    public void setBccs(List<EmailAddress> bccs) {
        this.bccs = bccs;
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
