/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.ws.fault;

import javax.xml.ws.WebFault;

/**
 *
 * @author Izam
 */
@WebFault(name = "AccountInfoFaultException", targetNamespace = "http://ws.etanah/")
public class AccountInfoFaultException extends Exception {
    private static final long serialVersionUID = 1L;
    private AccountInfoFault faultInfo;

    public AccountInfoFaultException(String message, AccountInfoFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public AccountInfoFaultException(String message, AccountInfoFault faultInfo,
            Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    public AccountInfoFault getFaultInfo() {
        return faultInfo;
    }
}
