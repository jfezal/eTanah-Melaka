/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah.ws.utility;

/**
 *
 * @author Izam
 */
public class StatusInfo {
    private String functionName;
    private String statusCode;
    private String statusMessage;
    private String noresit;

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

    public String getNoresit() {
        return noresit;
    }

    public void setNoresit(String noresit) {
        this.noresit = noresit;
    }
    
    
}
