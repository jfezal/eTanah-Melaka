
package etanah.client.bayar;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for StatusLogin complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatusLogin">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idPengguna" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nama" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tarikhterakhirlogin" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatusLogin", namespace = "http://ws.portal.view.etanah", propOrder = {
    "idPengguna",
    "nama",
    "sts",
    "tarikhterakhirlogin"
})
public class StatusLogin {

    @XmlElementRef(name = "idPengguna", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> idPengguna;
    @XmlElementRef(name = "nama", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> nama;
    @XmlElementRef(name = "sts", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> sts;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar tarikhterakhirlogin;

    /**
     * Gets the value of the idPengguna property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIdPengguna() {
        return idPengguna;
    }

    /**
     * Sets the value of the idPengguna property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIdPengguna(JAXBElement<String> value) {
        this.idPengguna = ((JAXBElement<String> ) value);
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
        this.nama = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the sts property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSts() {
        return sts;
    }

    /**
     * Sets the value of the sts property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSts(JAXBElement<String> value) {
        this.sts = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the tarikhterakhirlogin property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTarikhterakhirlogin() {
        return tarikhterakhirlogin;
    }

    /**
     * Sets the value of the tarikhterakhirlogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTarikhterakhirlogin(XMLGregorianCalendar value) {
        this.tarikhterakhirlogin = value;
    }

}
