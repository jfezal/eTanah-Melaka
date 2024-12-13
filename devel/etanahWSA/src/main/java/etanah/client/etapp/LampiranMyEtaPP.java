
package etanah.client.etapp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LampiranMyEtaPP complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LampiranMyEtaPP">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bytesEtaPP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="docTypeEtaPP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filenameEtaPP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kodDokumenEtaPP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LampiranMyEtaPP", namespace = "http://form.ws.etapp.view.etanah", propOrder = {
    "bytesEtaPP",
    "docTypeEtaPP",
    "filenameEtaPP",
    "kodDokumenEtaPP"
})
public class LampiranMyEtaPP {

    @XmlElementRef(name = "bytesEtaPP", namespace = "http://form.ws.etapp.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> bytesEtaPP;
    @XmlElementRef(name = "docTypeEtaPP", namespace = "http://form.ws.etapp.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> docTypeEtaPP;
    @XmlElementRef(name = "filenameEtaPP", namespace = "http://form.ws.etapp.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> filenameEtaPP;
    @XmlElementRef(name = "kodDokumenEtaPP", namespace = "http://form.ws.etapp.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> kodDokumenEtaPP;

    /**
     * Gets the value of the bytesEtaPP property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBytesEtaPP() {
        return bytesEtaPP;
    }

    /**
     * Sets the value of the bytesEtaPP property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBytesEtaPP(JAXBElement<String> value) {
        this.bytesEtaPP = value;
    }

    /**
     * Gets the value of the docTypeEtaPP property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDocTypeEtaPP() {
        return docTypeEtaPP;
    }

    /**
     * Sets the value of the docTypeEtaPP property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDocTypeEtaPP(JAXBElement<String> value) {
        this.docTypeEtaPP = value;
    }

    /**
     * Gets the value of the filenameEtaPP property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFilenameEtaPP() {
        return filenameEtaPP;
    }

    /**
     * Sets the value of the filenameEtaPP property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFilenameEtaPP(JAXBElement<String> value) {
        this.filenameEtaPP = value;
    }

    /**
     * Gets the value of the kodDokumenEtaPP property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getKodDokumenEtaPP() {
        return kodDokumenEtaPP;
    }

    /**
     * Sets the value of the kodDokumenEtaPP property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setKodDokumenEtaPP(JAXBElement<String> value) {
        this.kodDokumenEtaPP = value;
    }

}
