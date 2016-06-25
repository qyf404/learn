package com.qyf404.email.api;

import org.junit.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailTest {
    protected static String smtpHost;
    protected static int smtpPort;
    protected static String smtpUser;
    protected static String smtpPassword;


    protected static String subject = "test_subject";
    protected static String content = "test content";

    protected static String recipientEmail = "1@qq.com";
    protected final String carbonCopyEmail = "q@gmail.com";
    protected String blindCarbonCopyEmail = "q@163.com";


    @BeforeClass
    public static void setUp() throws IOException {

        Properties properties = new Properties();
        InputStream in = Object.class.getResourceAsStream("/email.properties");
        properties.load(in);

        smtpHost = properties.getProperty("smtp.host");
        smtpPort = Integer.parseInt(properties.getProperty("smtp.port"));
        smtpUser = properties.getProperty("smtp.user");
        smtpPassword = properties.getProperty("smtp.password");

    }
}
