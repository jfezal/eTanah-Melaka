
package etanah.view.portal.service.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SenaraiKodDaerah complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SenaraiKodDaerah">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="kodDaerah" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="namaDaerah" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SenaraiKodDaerah", propOrder = {
    "kodDaerah",
    "namaDaerah"
})
public class SenaraiKodDaerah {

    @XmlElementRef(name = "kodDaerah", namespace = "http://ws.service.portal.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> kodDaerah;
    @XmlElementRef(name = "namaDaerah", namespace = "http://ws.service.portal.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> namaDaerah;

    /**
     * Gets the value of the kodDaerah property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getKodDaerah() {
        return kodDaerah;
    }

    /**
     * Sets the value of the kodDaerah property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setKodDaerah(JAXBElement<String> value) {
        this.kodDaerah = value;
    }

    /**
     * Gets the value of the namaDaerah property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNamaDaerah() {
        return namaDaerah;
    }

    /**
     * Sets the value of the namaDaerah property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNamaDaerah(JAXBElement<String> value) {
        this.namaDaerah = value;
    }

}
