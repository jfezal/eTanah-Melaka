/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.mail.ws;

import etanah.mail.ws.MailClientService;
import etanah.mail.ws.service.EmailService;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.mail.MessagingException;

/**
 *
 * @author mohd.faidzal
 */
@WebService(endpointInterface = "etanah.mail.ws.MailClientService")
public class MailClientServiceImpl implements MailClientService {
    
    @Override
    public String sendMailAttachment(String[] toAddress, String subject, String message, String[] attachFiles) {
        EmailService service = new EmailService();
        try {
            service.sendEmailWithAttachments(toAddress, subject, message, attachFiles);
        } catch (MessagingException ex) {
            Logger.getLogger(MailClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MailClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    @Override
    public String sendEmailTemplate(String[] toAddress, String subject, String[] attachFiles, String namaFail, String tarikh, String cawangan) {
        EmailService service = new EmailService();
        try {
            service.sendEmailTemplate(toAddress, subject, attachFiles, namaFail, tarikh, cawangan);
        } catch (MessagingException ex) {
            Logger.getLogger(MailClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MailClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TemplateException ex) {
            Logger.getLogger(MailClientServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public void testMail() {
        EmailService service = new EmailService();
        service.test();

    }
    
}
