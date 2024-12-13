/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.view.uam;

import com.google.inject.Inject;
import etanah.Configuration;
import etanah.model.KodCawangan;
import etanah.model.KodDaerah;
import etanah.model.KodUrusan;
import etanah.model.Permohonan;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 *
 * @author faidzal
 */
public class MailService {

    private String host = new String();
    private String from;
    private String username;
    private String pass;
//    private String from = "etanah@ns.gov.my";
//    private String username = "etanah@ns.gov.my";
//    private String pass = "etanahns";
//    private String from = "EtanahSys@melaka.gov.my";
//    private String username = "EtanahSys";
//    private String pass = "456rtyfghVBN";
    @Inject
    private etanah.Configuration conf;

    public String HTMLJtek(Permohonan permohonan) {
        String msg = "Ulasan dari pihak tuan diperlukan bagi permohonan " + permohonan.getKodUrusan().getNama() + " untuk id permohonan " + permohonan.getIdPermohonan() + ".\n"
                + "Sila layari http://etanah.melaka.gov.my/jtek/ untuk memberi ulasan.\n"
                + "Terima Kasih";

        return msg;
    }

    public boolean sendEmailHTML1(String[] to, String subject, String text, String kodNegeri) {
        try {

//            host = "172.16.254.69";
//            host = "172.16.254.112";
//            host = "10.66.130.43";
            if (kodNegeri.equals("05")) {

                from = "etanah@ns.gov.my";
                username = "etanah@ns.gov.my";
                pass = "etanahns";
//                host = "10.55.2.15";
                host = "10.66.128.43";
                System.out.println("Negeri Sembilan");
                System.out.println("host:" + host);
            } else {
               from = "ptg@melaka.gov.my";
                username = "ptg";
//                pass = "456rtyfghVBN";
                pass = "abc123ABC123";
//                host = "10.66.130.43";
                host = "172.16.25.31";
                System.out.println("MElaka");
                System.out.println("host:" + host);
            }
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true"); // added this line
            props.put("mail.smtp.auth", "true");  // If you need to authenticate

            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            //Session session = Session.getDefaultInstance(props, null);
            javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, pass);
                }
            });
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) { // changed from a while loop
                toAddress[i] = new InternetAddress(to[i]);
            }
            System.out.println(Message.RecipientType.TO);

            for (int i = 0; i < toAddress.length; i++) { // changed from a while loop
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            message.setSubject(subject);
            //message.setText(text);
            message.setContent(text, "text/html");
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
//            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;

        } catch (MessagingException ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);

            return true;
        } catch (Exception mex) {
            mex.printStackTrace();
            return true;
        }

    }

    public boolean sendEmail(String[] to, String subject, String text, String kodNegeri) {
        try {

//            host = "172.16.254.69";
//            host = "172.16.254.112";
//            host = "10.66.130.43";
            if (kodNegeri.equals("05")) {

                from = "etanah@ns.gov.my";
                username = "etanah@ns.gov.my";
                pass = "etanahns";
//                host = "10.55.2.15";
                host = "10.66.128.43";
                System.out.println("Negeri Sembilan");
                System.out.println("host:" + host);
            } else {
                from = "ptg@melaka.gov.my";
                username = "ptg";
//                pass = "456rtyfghVBN";
                pass = "abc123ABC123";
//                host = "10.66.130.43";
                host = "172.16.25.31";
                System.out.println("MElaka");
                System.out.println("host:" + host);
            }
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "true"); // added this line
            props.put("mail.smtp.auth", "true");  // If you need to authenticate

            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");

            //Session session = Session.getDefaultInstance(props, null);
            javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, pass);
                }
            });
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) { // changed from a while loop
                toAddress[i] = new InternetAddress(to[i]);
            }
            System.out.println(Message.RecipientType.TO);

            for (int i = 0; i < toAddress.length; i++) { // changed from a while loop
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            message.setSubject(subject);
//             message.setContent(text, "text/html");
            message.setText(text);
            //   message.setContent(to, text);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
//            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;

        } catch (MessagingException ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);

            return true;
        } catch (Exception mex) {
            mex.printStackTrace();
            return true;
        }

    }

    public boolean sendEmailHTML(String[] to, String subject, String text, String kodNegeri) throws FileNotFoundException, IOException {
        try {

//            host = "172.16.254.69";
//            host = "172.16.254.112";
//            host = "10.66.130.43";
            if (kodNegeri.equals("05")) {

                from = "etanah@ns.gov.my";
                username = "etanah@ns.gov.my";
                pass = "etanahns";
//                host = "10.55.2.15";
                host = "10.66.128.43";
                System.out.println("Negeri Sembilan");
                System.out.println("host:" + host);
            } else {
                from = "EtanahSys@melaka.gov.my";
                username = "EtanahSys";
                pass = "456rtyfghVBN";
                host = "10.66.130.43";
                System.out.println("MElaka");
                System.out.println("host:" + host);
            }
            Properties props = System.getProperties();
            props.put("mail.smtp.starttls.enable", "false"); // added this line
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.user", from);
            props.put("mail.smtp.password", pass);
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.auth", "true");
            //Session session = Session.getDefaultInstance(props, null);
            javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, pass);
                }
            });
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) { // changed from a while loop
                toAddress[i] = new InternetAddress(to[i]);
            }
            System.out.println(Message.RecipientType.TO);

            for (int i = 0; i < toAddress.length; i++) { // changed from a while loop
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            message.setSubject(subject);
            message.setContent(text, "text/html");
            // message.setText(text);
            //   message.setContent(to, text);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
//            transport.connect();
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;

        } catch (MessagingException ex) {
            Logger.getLogger(MailService.class.getName()).log(Level.SEVERE, null, ex);

            return true;
        } catch (Exception mex) {
            mex.printStackTrace();
            return true;
        }
    }

    public boolean sendEmail(String to, String subject, String text, String kodNegeri) throws FileNotFoundException, IOException {
//        String to = "abcd@gmail.com";
//
//        // Sender's email ID needs to be mentioned
//        String from = "web@gmail.com";
//
//        // Assuming you are sending email from localhost
//        String host = "localhost";
//
//        // Get system properties
//        Properties properties = System.getProperties();
//
//        // Setup mail server
//        properties.setProperty("mail.smtp.host", host);
        if (kodNegeri.equals("05")) {

            from = "etanah@ns.gov.my";
            username = "etanah@ns.gov.my";
            pass = "etanahns";
//                host = "10.55.2.15";
            host = "10.66.128.43";
            System.out.println("Negeri Sembilan");
            System.out.println("host:" + host);
        } else {
            from = "EtanahSys@melaka.gov.my";
            username = "EtanahSys";
            pass = "456rtyfghVBN";
            host = "10.66.130.43";
            System.out.println("MElaka");
            System.out.println("host:" + host);
        }
        Properties props = System.getProperties();
//            props.put("mail.smtp.starttls.enable", "false"); // added this line
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "true");
        // Get the default Session object.
        javax.mail.Session session = javax.mail.Session.getInstance(props, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, pass);
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Create the message part 
            BodyPart messageBodyPart = new MimeBodyPart();

            // Fill the message
            messageBodyPart.setText("This is message body");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            File theFile = new File("C:/Tulips.jpg");
            InputStream in = new FileInputStream(theFile);
            long fail_length = theFile.length();

            MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();
            String jenis_mime = mimeTypesMap.getContentType(theFile);
            DataSource source = new ByteArrayDataSource(in, jenis_mime);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(theFile.getName());
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        MailService ms = new MailService();
        String[] s = {"mohd.faidzal@theta-edge.com"};
        Permohonan permohonan = new Permohonan();
        KodUrusan kod = new KodUrusan();
        kod.setNama("hihihi");
        kod.setKod("jkjk");
        KodCawangan caw = new KodCawangan();
        caw.setName("cawangan");
        caw.setKod("caw");
        KodDaerah daerah = new KodDaerah();
        daerah.setNama("daerah");
        daerah.setKod("koddaerah");
        caw.setDaerah(daerah);
        permohonan.setIdPermohonan("id");
        permohonan.setKodUrusan(kod);
        permohonan.setCawangan(caw);
        String text = ms.HTMLJtek(permohonan);
        ms.sendEmail(s, "sss", text, "04");
//        ms.s("10.66.130.43", "faidzal@etanahjv.com.my", "faidzal@etanahjv.com.my", "mdfaidzal@gmail.com", "mdfaidzal@gmail.com");
    }
//        String to = "mdfaidzal@gmail.com";
//
//        // Sender's email ID needs to be mentioned
//        String from = "web@gmail.com";
//
//        // Assuming you are sending email from localhost
//        String host = "10.66.130.43";
//
//        // Get system properties
//        Properties properties = System.getProperties();
//
//        // Setup mail server
//        properties.setProperty("mail.smtp.host", host);
//properties.setProperty("mail.smtp.port", "25");
//        // Get the default Session object.
//        Session session = Session.getDefaultInstance(properties);
//
//        try {
//            // Create a default MimeMessage object.
//            MimeMessage message = new MimeMessage(session);
//
//            // Set From: header field of the header.
//            message.setFrom(new InternetAddress(from));
//
//            // Set To: header field of the header.
//            message.addRecipient(Message.RecipientType.TO,
//                    new InternetAddress(to));
//
//            // Set Subject: header field
//            message.setSubject("This is the Subject Line!");
//
//            // Now set the actual message
//            message.setText("This is actual message");
//
//            // Send message
//            Transport.send(message);
//            System.out.println("Sent message successfully....");
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
//
//    }

    public void s(String smtpServer, String to, String from, String subject, String body) {
        try {
            send(smtpServer, to, from, subject, body);
        } catch (Exception ex) {
            System.out.println("Usage: java com.lotontech.mail.SimpleSender"
                    + " smtpServer toAddress fromAddress subjectText bodyText");
        }
    }

    /**
     * "send" method to send the message.
     */
    public static void send(String smtpServer, String to, String from, String subject, String body) {
        try {
            Properties props = System.getProperties();
            // -- Attaching to default Session, or we could start a new one --
            props.put("mail.smtp.host", smtpServer);
            Session session = Session.getDefaultInstance(props, null);
            // -- Create a new message --
            Message msg = new MimeMessage(session);
            // -- Set the FROM and TO fields --
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to, false));
            // -- We could include CC recipients too --
            // if (cc != null)
            // msg.setRecipients(Message.RecipientType.CC
            // ,InternetAddress.parse(cc, false));
            // -- Set the subject and body text --
            msg.setSubject(subject);
            msg.setText(body);
            // -- Set some other header information --
            msg.setHeader("X-Mailer", "LOTONtechEmail");
            msg.setSentDate(new Date());
            // -- Send the message --
            Transport.send(msg);
            System.out.println("Message sent OK.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Configuration getConf() {
        return conf;
    }

    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
