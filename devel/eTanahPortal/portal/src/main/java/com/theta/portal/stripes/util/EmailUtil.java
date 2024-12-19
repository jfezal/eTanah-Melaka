/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.theta.portal.stripes.util;

import com.google.inject.Inject;
import com.theta.portal.stripes.Configuration;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;

/**
 *
 * @author wan.fairul
 */
public class EmailUtil {

    private static Session session;
    @Inject
    Configuration conf;
    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(EmailUtil.class);

    public void send(String[] Recipient, String[] CcRecipient, String subject, String body, String type) {
        LOG.info("inside mail : START");
        String mail_host = conf.getProperty("mail.smtp.host");
        String mail_port = conf.getProperty("mail.smtp.port");
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", mail_host);
        props.put("mail.smtp.port", mail_port);

        Authenticator auth = new javax.mail.Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                String username = conf.getProperty("mail.smtp.user");
                String password = conf.getProperty("mail.smtp.pwd");
                return new PasswordAuthentication(username, password);
            }
        };
        javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props, auth);
        // uncomment for debugging infos to stdout
        // mailSession.setDebug(true);
        Transport transport;
        try {
            transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject(subject);
            message.setContent(body, type);
            message.setFrom(new InternetAddress(conf.getProperty("mail.from.address")));
            if (Recipient != null) {
                InternetAddress[] address = new InternetAddress[Recipient.length];
                for (int i = 0; i < Recipient.length; i++) {
                    address[i] = new InternetAddress();
                    address[i].setAddress(Recipient[i]);
                }
                message.setRecipients(Message.RecipientType.TO, address);
            }
            if (CcRecipient != null) {
                InternetAddress[] address = new InternetAddress[CcRecipient.length];
                for (int i = 0; i < CcRecipient.length; i++) {
                    address[i] = new InternetAddress();
                    address[i].setAddress(CcRecipient[i]);
                }
                message.setRecipients(Message.RecipientType.CC, address);
            }
            transport.connect();
            Transport.send(message);
            transport.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        LOG.info("inside mail: FINISH");
    }

}
