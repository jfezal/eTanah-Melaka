
package etanah.client.cukai.strata;

import java.math.BigDecimal;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CukaiPetakOnlinePortType", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CukaiPetakOnlinePortType {


    /**
     * 
     * @param in0
     * @return
     *     returns etanah.client.cukai.strata.ArrayOfCukaiStrataForm
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
    @RequestWrapper(localName = "semakCukaiStrataIdHakmilik", targetNamespace = "http://localhost:8080/cukaipetakonlineWS", className = "etanah.client.cukai.strata.SemakCukaiStrataIdHakmilik")
    @ResponseWrapper(localName = "semakCukaiStrataIdHakmilikResponse", targetNamespace = "http://localhost:8080/cukaipetakonlineWS", className = "etanah.client.cukai.strata.SemakCukaiStrataIdHakmilikResponse")
    public ArrayOfCukaiStrataForm semakCukaiStrataIdHakmilik(
        @WebParam(name = "in0", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in0);

    /**
     * 
     * @param in6
     * @param in5
     * @param in7
     * @param in0
     * @param in2
     * @param in1
     * @param in4
     * @param in3
     * @return
     *     returns etanah.client.cukai.strata.ArrayOfCukaiStrataForm
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
    @RequestWrapper(localName = "semakCukaiStrataByNoLot", targetNamespace = "http://localhost:8080/cukaipetakonlineWS", className = "etanah.client.cukai.strata.SemakCukaiStrataByNoLot")
    @ResponseWrapper(localName = "semakCukaiStrataByNoLotResponse", targetNamespace = "http://localhost:8080/cukaipetakonlineWS", className = "etanah.client.cukai.strata.SemakCukaiStrataByNoLotResponse")
    public ArrayOfCukaiStrataForm semakCukaiStrataByNoLot(
        @WebParam(name = "in0", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in0,
        @WebParam(name = "in1", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in1,
        @WebParam(name = "in2", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in2,
        @WebParam(name = "in3", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in3,
        @WebParam(name = "in4", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        Integer in4,
        @WebParam(name = "in5", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in5,
        @WebParam(name = "in6", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in6,
        @WebParam(name = "in7", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in7);

    /**
     * 
     * @param in6
     * @param in5
     * @param in0
     * @param in2
     * @param in1
     * @param in4
     * @param in3
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
    @RequestWrapper(localName = "terimaBayaran", targetNamespace = "http://localhost:8080/cukaipetakonlineWS", className = "etanah.client.cukai.strata.TerimaBayaran")
    @ResponseWrapper(localName = "terimaBayaranResponse", targetNamespace = "http://localhost:8080/cukaipetakonlineWS", className = "etanah.client.cukai.strata.TerimaBayaranResponse")
    public String terimaBayaran(
        @WebParam(name = "in0", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in0,
        @WebParam(name = "in1", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in1,
        @WebParam(name = "in2", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in2,
        @WebParam(name = "in3", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        BigDecimal in3,
        @WebParam(name = "in4", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in4,
        @WebParam(name = "in5", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in5,
        @WebParam(name = "in6", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in6);

    /**
     * 
     * @param in6
     * @param in5
     * @param in7
     * @param in0
     * @param in2
     * @param in1
     * @param in4
     * @param in3
     * @return
     *     returns etanah.client.cukai.strata.ArrayOfCukaiStrataForm
     */
    @WebMethod
    @WebResult(name = "out", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
    @RequestWrapper(localName = "semakCukaiStrataByParam", targetNamespace = "http://localhost:8080/cukaipetakonlineWS", className = "etanah.client.cukai.strata.SemakCukaiStrataByParam")
    @ResponseWrapper(localName = "semakCukaiStrataByParamResponse", targetNamespace = "http://localhost:8080/cukaipetakonlineWS", className = "etanah.client.cukai.strata.SemakCukaiStrataByParamResponse")
    public ArrayOfCukaiStrataForm semakCukaiStrataByParam(
        @WebParam(name = "in0", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in0,
        @WebParam(name = "in1", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in1,
        @WebParam(name = "in2", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in2,
        @WebParam(name = "in3", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in3,
        @WebParam(name = "in4", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in4,
        @WebParam(name = "in5", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in5,
        @WebParam(name = "in6", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in6,
        @WebParam(name = "in7", targetNamespace = "http://localhost:8080/cukaipetakonlineWS")
        String in7);

}
