package com.qyf404.email.domain;

import com.qyf404.email.api.Mail;
import com.qyf404.email.api.MailBuilder;
import com.qyf404.email.api.MailSpecification;

import java.util.ArrayList;
import java.util.List;

public class DefaultMailBuilder implements MailBuilder {

    private MailSpecification mailSpecification;

    private String subject;
    private List<String> recipients = new ArrayList<String>();
    private List<String> carbonCopies = new ArrayList<String>();
    private List<String> blindCarbonCopies = new ArrayList<String>();
    private String content;

    private DefaultMailBuilder() {
        this.mailSpecification = new DefaultMailSpecification();
    }

    public static MailBuilder createInstance() {
        return new DefaultMailBuilder();
    }

    @Override
    public MailBuilder withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    @Override
    public MailBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public MailBuilder addRecipientEmail(String recipientEmail) {
        recipients.add(recipientEmail);
        return this;
    }

    @Override
    public MailBuilder addCarbonCopyEmail(String carbonCopieEmail) {
        carbonCopies.add(carbonCopieEmail);
        return this;
    }

    @Override
    public MailBuilder addBlindCarbonCopyEmail(String blindCarbonCopyEmail) {
        blindCarbonCopies.add(blindCarbonCopyEmail);
        return this;
    }

    @Override
    public Mail build() throws BuildException {
        TextMail mail = new TextMail(subject, recipients, carbonCopies, blindCarbonCopies, content);

        if (!this.mailSpecification.isSatisfiedBy(mail)) {
            throw new BuildException();
        }

        return mail;
    }


}
