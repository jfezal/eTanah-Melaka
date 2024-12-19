
package etanah.view.portal.service.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PengumumanForm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PengumumanForm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pengumuman" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tajuk" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PengumumanForm", propOrder = {
    "pengumuman",
    "tajuk"
})
public class PengumumanForm {

    @XmlElementRef(name = "pengumuman", namespace = "http://ws.service.portal.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pengumuman;
    @XmlElementRef(name = "tajuk", namespace = "http://ws.service.portal.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> tajuk;

    /**
     * Gets the value of the pengumuman property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPengumuman() {
        return pengumuman;
    }

    /**
     * Sets the value of the pengumuman property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPengumuman(JAXBElement<String> value) {
        this.pengumuman = value;
    }

    /**
     * Gets the value of the tajuk property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTajuk() {
        return tajuk;
    }

    /**
     * Sets the value of the tajuk property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTajuk(JAXBElement<String> value) {
        this.tajuk = value;
    }

}
