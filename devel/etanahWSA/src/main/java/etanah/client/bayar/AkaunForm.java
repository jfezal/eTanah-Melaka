
package etanah.client.bayar;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AkaunForm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AkaunForm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amaunt" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="noAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AkaunForm", namespace = "http://ws.portal.view.etanah", propOrder = {
    "amaunt",
    "noAccount"
})
public class AkaunForm {

    @XmlElementRef(name = "amaunt", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> amaunt;
    @XmlElementRef(name = "noAccount", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> noAccount;

    /**
     * Gets the value of the amaunt property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getAmaunt() {
        return amaunt;
    }

    /**
     * Sets the value of the amaunt property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setAmaunt(JAXBElement<BigDecimal> value) {
        this.amaunt = ((JAXBElement<BigDecimal> ) value);
    }

    /**
     * Gets the value of the noAccount property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNoAccount() {
        return noAccount;
    }

    /**
     * Sets the value of the noAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNoAccount(JAXBElement<String> value) {
        this.noAccount = ((JAXBElement<String> ) value);
    }

}
