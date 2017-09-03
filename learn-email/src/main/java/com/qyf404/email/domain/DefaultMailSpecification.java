package com.qyf404.email.domain;

import com.qyf404.email.api.Mail;
import com.qyf404.email.api.MailSpecification;

public class DefaultMailSpecification implements MailSpecification {
    @Override
    public boolean isSatisfiedBy(Mail mail) {
        if (mail.getSubject() == null) {
            return false;
        }

        if (mail.getContent() == null) {
            return false;
        }

        if (mail.getRecipients() == null || mail.getRecipients().size() == 0) {
            return false;
        }

        return true;
    }
}
