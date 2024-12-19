
package etanah.view.portal.service.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SenaraiKodSeksyen complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SenaraiKodSeksyen">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bpm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nama" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="seksyen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SenaraiKodSeksyen", propOrder = {
    "bpm",
    "kod",
    "nama",
    "seksyen"
})
public class SenaraiKodSeksyen {

    @XmlElementRef(name = "bpm", namespace = "http://ws.service.portal.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> bpm;
    @XmlElementRef(name = "kod", namespace = "http://ws.service.portal.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> kod;
    @XmlElementRef(name = "nama", namespace = "http://ws.service.portal.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> nama;
    @XmlElementRef(name = "seksyen", namespace = "http://ws.service.portal.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> seksyen;

    /**
     * Gets the value of the bpm property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBpm() {
        return bpm;
    }

    /**
     * Sets the value of the bpm property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBpm(JAXBElement<String> value) {
        this.bpm = value;
    }

    /**
     * Gets the value of the kod property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getKod() {
        return kod;
    }

    /**
     * Sets the value of the kod property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setKod(JAXBElement<String> value) {
        this.kod = value;
    }

    /**
     * Gets the value of the nama property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNama() {
        return nama;
    }

    /**
     * Sets the value of the nama property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNama(JAXBElement<String> value) {
        this.nama = value;
    }

    /**
     * Gets the value of the seksyen property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSeksyen() {
        return seksyen;
    }

    /**
     * Sets the value of the seksyen property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSeksyen(JAXBElement<String> value) {
        this.seksyen = value;
    }

}
