
package etanah.view.portal.service.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StatusTukarGanti complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatusTukarGanti">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="daerah" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="diDaftarOleh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idHakmilik" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noLot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tarikh" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="versi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusTukarGanti", propOrder = {
    "daerah",
    "diDaftarOleh",
    "idHakmilik",
    "noLot",
    "tarikh",
    "versi"
})
public class StatusTukarGanti {

    @XmlElementRef(name = "daerah", namespace = "http://ws.service.portal.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> daerah;
    @XmlElementRef(name = "diDaftarOleh", namespace = "http://ws.service.portal.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> diDaftarOleh;
    @XmlElementRef(name = "idHakmilik", namespace = "http://ws.service.portal.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> idHakmilik;
    @XmlElementRef(name = "noLot", namespace = "http://ws.service.portal.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> noLot;
    @XmlElementRef(name = "tarikh", namespace = "http://ws.service.portal.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> tarikh;
    @XmlElementRef(name = "versi", namespace = "http://ws.service.portal.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> versi;

    /**
     * Gets the value of the daerah property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDaerah() {
        return daerah;
    }

    /**
     * Sets the value of the daerah property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDaerah(JAXBElement<String> value) {
        this.daerah = value;
    }

    /**
     * Gets the value of the diDaftarOleh property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDiDaftarOleh() {
        return diDaftarOleh;
    }

    /**
     * Sets the value of the diDaftarOleh property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDiDaftarOleh(JAXBElement<String> value) {
        this.diDaftarOleh = value;
    }

    /**
     * Gets the value of the idHakmilik property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdHakmilik() {
        return idHakmilik;
    }

    /**
     * Sets the value of the idHakmilik property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdHakmilik(JAXBElement<String> value) {
        this.idHakmilik = value;
    }

    /**
     * Gets the value of the noLot property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNoLot() {
        return noLot;
    }

    /**
     * Sets the value of the noLot property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNoLot(JAXBElement<String> value) {
        this.noLot = value;
    }

    /**
     * Gets the value of the tarikh property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTarikh() {
        return tarikh;
    }

    /**
     * Sets the value of the tarikh property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTarikh(JAXBElement<String> value) {
        this.tarikh = value;
    }

    /**
     * Gets the value of the versi property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVersi() {
        return versi;
    }

    /**
     * Sets the value of the versi property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVersi(JAXBElement<String> value) {
        this.versi = value;
    }

}
