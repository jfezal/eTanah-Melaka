/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.ws.fault;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Izam
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "functionName",
    "statusCode",
    "statusMessage"
})
@XmlRootElement(name = "AccountInfoFault")
public class AccountInfoFault implements Serializable {
    private static final long serialVersionUID = 1;
    
    @XmlElement(required = true)
    private String functionName;

    @XmlElement(required = true)
    private String statusCode;
    
    @XmlElement(required = true)
    private String statusMessage;

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
