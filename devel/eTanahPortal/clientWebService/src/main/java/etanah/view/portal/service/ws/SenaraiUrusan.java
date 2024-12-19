
package etanah.view.portal.service.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SenaraiUrusan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SenaraiUrusan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="kodUrusan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="namUrusan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SenaraiUrusan", propOrder = {
    "kodUrusan",
    "namUrusan"
})
public class SenaraiUrusan {

    @XmlElementRef(name = "kodUrusan", namespace = "http://ws.service.portal.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> kodUrusan;
    @XmlElementRef(name = "namUrusan", namespace = "http://ws.service.portal.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> namUrusan;

    /**
     * Gets the value of the kodUrusan property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getKodUrusan() {
        return kodUrusan;
    }

    /**
     * Sets the value of the kodUrusan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setKodUrusan(JAXBElement<String> value) {
        this.kodUrusan = value;
    }

    /**
     * Gets the value of the namUrusan property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNamUrusan() {
        return namUrusan;
    }

    /**
     * Sets the value of the namUrusan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNamUrusan(JAXBElement<String> value) {
        this.namUrusan = value;
    }

}
