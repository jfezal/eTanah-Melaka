
package etanah.integration.myetapp;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "EtappClientService", targetNamespace = "http://ws.etapp.etanah/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EtappClientService {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns etanah.integration.myetapp.StatusForm
     */
    @WebMethod
    @WebResult(partName = "return")
    public StatusForm hantarBorangA(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns etanah.integration.myetapp.StatusForm
     */
    @WebMethod
    @WebResult(partName = "return")
    public StatusForm hantarBorangB(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns etanah.integration.myetapp.StatusForm
     */
    @WebMethod
    @WebResult(partName = "return")
    public StatusForm hantarMMK(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns etanah.integration.myetapp.StatusForm
     */
    @WebMethod
    @WebResult(partName = "return")
    public StatusForm hantarBorangD(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns etanah.integration.myetapp.StatusForm
     */
    @WebMethod
    @WebResult(partName = "return")
    public StatusForm hantarBorangK(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns etanah.integration.myetapp.StatusForm
     */
    @WebMethod
    @WebResult(partName = "return")
    public StatusForm hantarSijilUkur(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns etanah.integration.myetapp.StatusForm
     */
    @WebMethod
    @WebResult(partName = "return")
    public StatusForm hantarHakmilikSambungan(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1);

}