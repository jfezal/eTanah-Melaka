
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
 *         &lt;element name="in0" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="in1" type="{http://form.ws.etapp.view.etanah}LampiranMyEtaPP"/>
 *         &lt;element name="in2" type="{http://form.ws.etapp.view.etanah}ArrayOfLampiranMyEtaPP"/>
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
    "in2"
})
@XmlRootElement(name = "borangCdanMMKMyEtaPP")
public class BorangCdanMMKMyEtaPP {

    @XmlElement(required = true, nillable = true)
    protected String in0;
    @XmlElement(required = true, nillable = true)
    protected LampiranMyEtaPP in1;
    @XmlElement(required = true, nillable = true)
    protected ArrayOfLampiranMyEtaPP in2;

    /**
     * Gets the value of the in0 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIn0() {
        return in0;
    }

    /**
     * Sets the value of the in0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIn0(String value) {
        this.in0 = value;
    }

    /**
     * Gets the value of the in1 property.
     * 
     * @return
     *     possible object is
     *     {@link LampiranMyEtaPP }
     *     
     */
    public LampiranMyEtaPP getIn1() {
        return in1;
    }

    /**
     * Sets the value of the in1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link LampiranMyEtaPP }
     *     
     */
    public void setIn1(LampiranMyEtaPP value) {
        this.in1 = value;
    }

    /**
     * Gets the value of the in2 property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfLampiranMyEtaPP }
     *     
     */
    public ArrayOfLampiranMyEtaPP getIn2() {
        return in2;
    }

    /**
     * Sets the value of the in2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfLampiranMyEtaPP }
     *     
     */
    public void setIn2(ArrayOfLampiranMyEtaPP value) {
        this.in2 = value;
    }

}
