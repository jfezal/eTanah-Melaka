
package etanah.client.etapp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="in0" type="{http://form.ws.etapp.view.etanah}MaklumatPermohonanSek8MyEtaPP"/>
 *         &lt;element name="in1" type="{http://form.ws.etapp.view.etanah}ArrayOfHakmilikPermohonanMyEtaPP"/>
 *         &lt;element name="in2" type="{http://form.ws.etapp.view.etanah}LampiranMyEtaPP"/>
 *         &lt;element name="in3" type="{http://form.ws.etapp.view.etanah}ArrayOfLampiranMyEtaPP"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "in0",
    "in1",
    "in2",
    "in3"
})
@XmlRootElement(name = "daftarPermohonanSek8MyEtaPP")
public class DaftarPermohonanSek8MyEtaPP {

    @XmlElement(required = true, nillable = true)
    protected MaklumatPermohonanSek8MyEtaPP in0;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfHakmilikPermohonanMyEtaPP in1;
    @XmlElement(required = true, nillable = true)
    protected LampiranMyEtaPP in2;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfLampiranMyEtaPP in3;

    /**
     * Gets the value of the in0 property.
     * 
     * @return
     *     possible object is
     *     {@link MaklumatPermohonanSek8MyEtaPP }
     *     
     */
    public MaklumatPermohonanSek8MyEtaPP getIn0() {
        return in0;
    }

    /**
     * Sets the value of the in0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link MaklumatPermohonanSek8MyEtaPP }
     *     
     */
    public void setIn0(MaklumatPermohonanSek8MyEtaPP value) {
        this.in0 = value;
    }

    /**
     * Gets the value of the in1 property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfHakmilikPermohonanMyEtaPP }
     *     
     */
    public ArrayOfHakmilikPermohonanMyEtaPP getIn1() {
        return in1;
    }

    /**
     * Sets the value of the in1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfHakmilikPermohonanMyEtaPP }
     *     
     */
    public void setIn1(ArrayOfHakmilikPermohonanMyEtaPP value) {
        this.in1 = value;
    }

    /**
     * Gets the value of the in2 property.
     * 
     * @return
     *     possible object is
     *     {@link LampiranMyEtaPP }
     *     
     */
    public LampiranMyEtaPP getIn2() {
        return in2;
    }

    /**
     * Sets the value of the in2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link LampiranMyEtaPP }
     *     
     */
    public void setIn2(LampiranMyEtaPP value) {
        this.in2 = value;
    }

    /**
     * Gets the value of the in3 property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfLampiranMyEtaPP }
     *     
     */
    public ArrayOfLampiranMyEtaPP getIn3() {
        return in3;
    }

    /**
     * Sets the value of the in3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfLampiranMyEtaPP }
     *     
     */
    public void setIn3(ArrayOfLampiranMyEtaPP value) {
        this.in3 = value;
    }

}
