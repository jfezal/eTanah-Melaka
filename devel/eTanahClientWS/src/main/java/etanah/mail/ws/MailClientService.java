/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.mail.ws;

import etanah.etapp.ws.form.StatusForm;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author mohd.faidzal
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MailClientService {
    @WebMethod
    String sendMailAttachment(String []toAddress,
            String subject, String message, String[] attachFiles);
    
    @WebMethod
    String sendEmailTemplate(String[] toAddress,
            String subject, String[] attachFiles, String namaFail,String tarikh,String cawangan);
    
    @WebMethod
    void testMail();
}
