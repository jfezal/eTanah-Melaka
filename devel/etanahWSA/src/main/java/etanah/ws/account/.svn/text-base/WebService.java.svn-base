/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.ws.account;

import etanah.ws.fault.AccountInfoFault;
import etanah.ws.fault.AccountInfoFaultException;

/**
 *
 * @author Izam
 */
public class WebService {
    public void throwEmptyFaultException(String functionName)
            throws AccountInfoFaultException {
        String error = "QuitRentService search result(s) are emtpy";

        AccountInfoFault fault = new AccountInfoFault();
        fault.setFunctionName(functionName);
        fault.setStatusCode("EMPTY");
        fault.setStatusMessage(error);

        throw new AccountInfoFaultException(error, fault);
    }

    public void throwInternalFaultException(String functionName)
            throws AccountInfoFaultException {
        String error = "QuitRentService internal error occured";

        AccountInfoFault fault = new AccountInfoFault();
        fault.setFunctionName(functionName);
        fault.setStatusCode("INT_ERR");
        fault.setStatusMessage(error);
        
        throw new AccountInfoFaultException(error, fault);
    }

    public void throwValFaultException(String functionName)
            throws AccountInfoFaultException {
        String error = "QuitRentService validation exception occured";

        AccountInfoFault fault = new AccountInfoFault();
        fault.setFunctionName(functionName);
        fault.setStatusCode("VAL_ERR");
        fault.setStatusMessage(error);

        throw new AccountInfoFaultException(error, fault);
    }
}
