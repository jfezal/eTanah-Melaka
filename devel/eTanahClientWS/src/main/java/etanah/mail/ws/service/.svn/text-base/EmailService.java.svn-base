/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.mail.ws.service;

/**
 *
 * @author mohd.faidzal
 */
import freemarker.cache.FileTemplateLoader;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailService {

    final static String host = "172.16.25.31";
    final static String port = "587";
    final static String userName = "ptg@melaka.gov.my";
    final static String password = "abc123ABC123";
    private Properties prop;

    public void sendEmailTemplate(String[] toAddress,
            String subject, String[] attachFiles, String namaFail, String tarikh, String cawangan) throws TemplateException, IOException, MessagingException {
        initProp();
        freemarker.template.Configuration cfg = new freemarker.template.Configuration();
        FileTemplateLoader templateLoader = new FileTemplateLoader(new File(prop.getProperty("ispeks_hantar")));
        cfg.setTemplateLoader(templateLoader);
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        Template template = cfg.getTemplate("ispeks_hantar.ftl");
        Map<String, Object> rootMap = new HashMap<String, Object>();
        rootMap.put("namaFail", namaFail);
        rootMap.put("tarikh", tarikh);
        rootMap.put("cawangan", cawangan);
        Writer out = new StringWriter();
        template.process(rootMap, out);

        sendEmailWithAttachments(toAddress,
                subject, out.toString(), attachFiles);
    }

    public void sendEmailWithAttachments(String[] toAddress,
            String subject, String message, String[] attachFiles)
            throws AddressException, MessagingException, IOException {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
        properties.put("mail.smtp.ssl.trust", "*");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = new InternetAddress[toAddress.length];
        for (int i = 0; i < toAddress.length; i++) {
            String to = toAddress[i];
            toAddresses[i] = new InternetAddress(to);

        }

        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());

        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");

        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();

                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                multipart.addBodyPart(attachPart);
            }
        }

        // sets the multi-part as e-mail's content
        msg.setContent(multipart);

        // sends the e-mail
        Transport.send(msg);

    }

    /**
     * Test sending e-mail with attachments
     */
    public static void main(String[] args) throws IOException, TemplateException {
        EmailService e = new EmailService();
        // SMTP info
        String host = "172.16.25.31";
        String port = "587";
        String mailFrom = "ptg@melaka.gov.my";
        String password = "abc123ABC123";
//  from = "ptg@melaka.gov.my";
//                username = "ptg";
////                pass = "456rtyfghVBN";
//                pass = "abc123ABC123";
////                host = "10.66.130.43";
//                host = "172.16.25.31";
        System.out.println("MElaka");
        // message info
        String mailTo[] = new String[2];
//        freemarker.template.Configuration cfg = new freemarker.template.Configuration();
//                FileTemplateLoader templateLoader = new FileTemplateLoader(new File("C:/Users/mohd.faidzal/Downloads"));
//                cfg.setTemplateLoader(templateLoader);
//                cfg.setObjectWrapper(new DefaultObjectWrapper());
//                Template template = cfg.getTemplate("ispeks_hantar.ftl");
//                Map<String, Object> rootMap = new HashMap<String, Object>();
//                rootMap.put("namaFail", "john");
//                rootMap.put("tarikh", "sa");
//                rootMap.put("cawangan", "sa");
//                Writer out = new StringWriter();
//                template.process(rootMap, out);
        mailTo[0] = "ptg@melaka.gov.my";
        mailTo[1] = "mdfaidzal@gmail.com";
        String subject = "New email with attachments";
        String message = "I have some attachments for you.";

        // attachments
        String[] attachFiles = new String[3];
        attachFiles[0] = "C:/Users/mohd.faidzal/Downloads/WhatsApp.jpeg";
        attachFiles[1] = "C:/Users/mohd.faidzal/Downloads/WhatsApp.jpeg";
        attachFiles[2] = "C:/Users/mohd.faidzal/Downloads/WhatsApp.jpeg";

        try {
//            e.sendEmailWithAttachments( mailTo,
//                    subject, out.toString(), attachFiles);
            e.sendEmailTemplate(mailTo, subject, attachFiles, "john", "sa", "sa");
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }

    public void initProp() {
        FileReader reader;
        try {
            reader = new FileReader("files.properties");
            prop = new Properties();
            prop.load(reader);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void test() {
        EmailService e = new EmailService();
        // SMTP info
        String host = "172.16.25.31";
        String port = "587";
        String mailFrom = "ptg@melaka.gov.my";
        String password = "abc123ABC123";
//  from = "ptg@melaka.gov.my";
//                username = "ptg";
////                pass = "456rtyfghVBN";
//                pass = "abc123ABC123";
////                host = "10.66.130.43";
//                host = "172.16.25.31";
        System.out.println("MElaka");
        // message info
        String mailTo[] = new String[2];
//        freemarker.template.Configuration cfg = new freemarker.template.Configuration();
//                FileTemplateLoader templateLoader = new FileTemplateLoader(new File("C:/Users/mohd.faidzal/Downloads"));
//                cfg.setTemplateLoader(templateLoader);
//                cfg.setObjectWrapper(new DefaultObjectWrapper());
//                Template template = cfg.getTemplate("ispeks_hantar.ftl");
//                Map<String, Object> rootMap = new HashMap<String, Object>();
//                rootMap.put("namaFail", "john");
//                rootMap.put("tarikh", "sa");
//                rootMap.put("cawangan", "sa");
//                Writer out = new StringWriter();
//                template.process(rootMap, out);
        mailTo[0] = "ptg@melaka.gov.my";
        mailTo[1] = "mdfaidzal@gmail.com";
        String subject = "New email with attachments";
        String message = "I have some attachments for you.";

        // attachments
//        String[] attachFiles = new String[3];
//        attachFiles[0] = "C:/Users/mohd.faidzal/Downloads/WhatsApp.jpeg";
//        attachFiles[1] = "C:/Users/mohd.faidzal/Downloads/WhatsApp.jpeg";
//        attachFiles[2] = "C:/Users/mohd.faidzal/Downloads/WhatsApp.jpeg";
 String[] attachFiles = null;
        try {
//            e.sendEmailWithAttachments( mailTo,
//                    subject, out.toString(), attachFiles);
            e.sendEmailTemplate(mailTo, subject, attachFiles, "john", "sa", "sa");
            System.out.println("Email sent.");
        } catch (Exception ex) {
            System.out.println("Could not send email.");
            ex.printStackTrace();
        }
    }
}
