package com.qyf404.email.api;


import com.qyf404.email.domain.DefaultMailBuilder;
import com.qyf404.email.domain.DefaultMailSender;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import javax.mail.MessagingException;

public class MailSenderTest extends MailTest {

    @Ignore
    @Test
    public void should_send_email() {
        MailSender mailSender = new DefaultMailSender(smtpHost, smtpPort, smtpUser, smtpPassword);

        MailBuilder mailBuilder = DefaultMailBuilder.createInstance()
                .withSubject(subject)
                .withContent(content)
                .addRecipientEmail(recipientEmail)
                .addBlindCarbonCopyEmail(blindCarbonCopyEmail)
                .addCarbonCopyEmail(carbonCopyEmail);


        Mail mail = null;
        try {
            mail = mailBuilder.build();
        } catch (MailBuilder.BuildException buildException) {
            buildException.printStackTrace();
            Assert.fail();
        }

        try {
            mailSender.send(mail);
        } catch (MessagingException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }


}