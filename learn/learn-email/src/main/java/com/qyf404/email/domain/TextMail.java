package com.qyf404.email.domain;

import com.qyf404.email.api.Mail;

import java.util.ArrayList;
import java.util.List;

public class TextMail implements Mail {
    private String subject;
    private List<String> recipients = new ArrayList<String>();
    private List<String> carbonCopies = new ArrayList<String>();
    private List<String> blindCarbonCopies = new ArrayList<String>();
    private String content;

    TextMail(String subject, List<String> recipients, List<String> carbonCopies, List<String> blindCarbonCopies, String content) {
        this.subject = subject;
        this.recipients = recipients;
        this.carbonCopies = carbonCopies;
        this.blindCarbonCopies = blindCarbonCopies;
        this.content = content;
    }


    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public List<String> getRecipients() {
        return recipients;
    }

    @Override
    public List<String> getCarbonCopies() {
        return carbonCopies;
    }

    @Override
    public List<String> getBlindCarbonCopies() {
        return blindCarbonCopies;
    }

    @Override
    public String getContent() {
        return content;
    }
}
