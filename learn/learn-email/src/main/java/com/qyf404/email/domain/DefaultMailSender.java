package com.qyf404.email.domain;

import com.qyf404.email.api.Mail;
import com.qyf404.email.api.MailSender;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class DefaultMailSender implements MailSender {
    private String smtpHost;
    private int smtpPort;
    private String user;
    private String password;


    public DefaultMailSender(String smtpHost, int smtpPort, String user, String password) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.user = user;
        this.password = password;
    }

    @Override
    public void send(Mail mail) throws MessagingException {
        final Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.auth", "true");
        props.put("mail.user", user);
        props.put("mail.password", password);

//        props.put("mail.debug", true);

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(user));

        for (String to : mail.getRecipients()) {
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        }

        for (String cc : mail.getCarbonCopies()) {
            msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
        }

        for (String bcc : mail.getBlindCarbonCopies()) {
            msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
        }

        msg.setSubject(mail.getSubject());
        msg.setText(mail.getContent());
        msg.setSentDate(new Date());

        Transport.send(msg);

    }
}
