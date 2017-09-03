package com.qyf404.email.api;

public interface MailBuilder {

    MailBuilder withSubject(String subject);

    MailBuilder withContent(String content);

    MailBuilder addRecipientEmail(String recipientEmail);

    MailBuilder addCarbonCopyEmail(String carbonCopieEmail);

    MailBuilder addBlindCarbonCopyEmail(String blindCarbonCopyEmail);

    Mail build() throws BuildException;

    class BuildException extends Exception {
    }
}
