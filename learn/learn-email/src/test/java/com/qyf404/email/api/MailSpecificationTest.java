package com.qyf404.email.api;

import com.qyf404.email.domain.DefaultMailBuilder;
import org.junit.Assert;
import org.junit.Test;

public class MailSpecificationTest extends MailTest {
    @Test
    public void should_fail_send_email_when_subject_is_empty() {
        MailBuilder mailBuilder = DefaultMailBuilder.createInstance()
                .withSubject(null)
                .withContent(content)
                .addRecipientEmail(recipientEmail)
                .addBlindCarbonCopyEmail(blindCarbonCopyEmail)
                .addCarbonCopyEmail(carbonCopyEmail);

        try {
            Mail mail = mailBuilder.build();
            Assert.fail();
        } catch (MailBuilder.BuildException buildException) {
            buildException.printStackTrace();
        }
    }


    @Test
    public void should_fail_send_email_when_content_is_empty() {


        MailBuilder mailBuilder = DefaultMailBuilder.createInstance()
                .withSubject(subject)
                .withContent(null)
                .addRecipientEmail(recipientEmail)
                .addBlindCarbonCopyEmail(blindCarbonCopyEmail)
                .addCarbonCopyEmail(carbonCopyEmail);

        try {
            Mail mail = mailBuilder.build();
            Assert.fail();
        } catch (MailBuilder.BuildException buildException) {
            buildException.printStackTrace();
        }
    }


    public void should_fial_send_email_when_email_address_is_invalid() {
    }


    public void should_fial_send_email_when_subject_length_is_too_long() {
    }
}