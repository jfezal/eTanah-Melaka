
package etanah.ws.ispeks.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "WsIspeksPortType", targetNamespace = "http://localhost:8080/ispeksWS")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WsIspeksPortType {


    /**
     * 
     * @param in1
     * @param in0
     * @return
     *     returns java.lang.Boolean
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://localhost:8080/ispeksWS")
    @RequestWrapper(localName = "extractToDatabase", targetNamespace = "http://localhost:8080/ispeksWS", className = "etanah.ws.ispeks.client.ExtractToDatabase")
    @ResponseWrapper(localName = "extractToDatabaseResponse", targetNamespace = "http://localhost:8080/ispeksWS", className = "etanah.ws.ispeks.client.ExtractToDatabaseResponse")
    public Boolean extractToDatabase(
        @WebParam(name = "in0", targetNamespace = "http://localhost:8080/ispeksWS")
        String in0,
        @WebParam(name = "in1", targetNamespace = "http://localhost:8080/ispeksWS")
        String in1);

}