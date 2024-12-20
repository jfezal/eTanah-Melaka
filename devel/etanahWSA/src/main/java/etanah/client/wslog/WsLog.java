
package etanah.client.wslog;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "WsLog", targetNamespace = "http://localhost:8080/logWS", wsdlLocation = "http://localhost:8093/etanahViewController/services/WsLog?wsdl")
public class WsLog
    extends Service
{

    private final static URL WSLOG_WSDL_LOCATION;
    private final static WebServiceException WSLOG_EXCEPTION;
    private final static QName WSLOG_QNAME = new QName("http://localhost:8080/logWS", "WsLog");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8093/etanahViewController/services/WsLog?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        WSLOG_WSDL_LOCATION = url;
        WSLOG_EXCEPTION = e;
    }

    public WsLog() {
        super(__getWsdlLocation(), WSLOG_QNAME);
    }

    public WsLog(WebServiceFeature... features) {
        super(__getWsdlLocation(), WSLOG_QNAME, features);
    }

    public WsLog(URL wsdlLocation) {
        super(wsdlLocation, WSLOG_QNAME);
    }

    public WsLog(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, WSLOG_QNAME, features);
    }

    public WsLog(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WsLog(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WsLogPortType
     */
    @WebEndpoint(name = "WsLogHttpPort")
    public WsLogPortType getWsLogHttpPort() {
        return super.getPort(new QName("http://localhost:8080/logWS", "WsLogHttpPort"), WsLogPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WsLogPortType
     */
    @WebEndpoint(name = "WsLogHttpPort")
    public WsLogPortType getWsLogHttpPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://localhost:8080/logWS", "WsLogHttpPort"), WsLogPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (WSLOG_EXCEPTION!= null) {
            throw WSLOG_EXCEPTION;
        }
        return WSLOG_WSDL_LOCATION;
    }

}
