package com.qyf404.email.api;

import javax.mail.MessagingException;

public interface MailSender {
    void send(Mail mail) throws MessagingException;
}
