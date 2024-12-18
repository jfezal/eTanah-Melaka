/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah.gpg.pgpfile;

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
public interface DecryptService {
    
    @WebMethod
    StatusForm decrypt(String encryptPath, String decryptedPath);
    
    @WebMethod
    String status();
    
}
