
package etanah.client.etapp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MaklumatPenyerah complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MaklumatPenyerah">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alamat1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alamat2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alamat3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alamat4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kodNegeri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nama" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="poskod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MaklumatPenyerah", namespace = "http://ws.etapp.view.etanah", propOrder = {
    "alamat1",
    "alamat2",
    "alamat3",
    "alamat4",
    "kodNegeri",
    "nama",
    "poskod"
})
public class MaklumatPenyerah {

    @XmlElementRef(name = "alamat1", namespace = "http://ws.etapp.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> alamat1;
    @XmlElementRef(name = "alamat2", namespace = "http://ws.etapp.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> alamat2;
    @XmlElementRef(name = "alamat3", namespace = "http://ws.etapp.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> alamat3;
    @XmlElementRef(name = "alamat4", namespace = "http://ws.etapp.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> alamat4;
    @XmlElementRef(name = "kodNegeri", namespace = "http://ws.etapp.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> kodNegeri;
    @XmlElementRef(name = "nama", namespace = "http://ws.etapp.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> nama;
    @XmlElementRef(name = "poskod", namespace = "http://ws.etapp.view.etanah", type = JAXBElement.class, required = false)
    protected JAXBElement<String> poskod;

    /**
     * Gets the value of the alamat1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAlamat1() {
        return alamat1;
    }

    /**
     * Sets the value of the alamat1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAlamat1(JAXBElement<String> value) {
        this.alamat1 = value;
    }

    /**
     * Gets the value of the alamat2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAlamat2() {
        return alamat2;
    }

    /**
     * Sets the value of the alamat2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAlamat2(JAXBElement<String> value) {
        this.alamat2 = value;
    }

    /**
     * Gets the value of the alamat3 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAlamat3() {
        return alamat3;
    }

    /**
     * Sets the value of the alamat3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAlamat3(JAXBElement<String> value) {
        this.alamat3 = value;
    }

    /**
     * Gets the value of the alamat4 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAlamat4() {
        return alamat4;
    }

    /**
     * Sets the value of the alamat4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAlamat4(JAXBElement<String> value) {
        this.alamat4 = value;
    }

    /**
     * Gets the value of the kodNegeri property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getKodNegeri() {
        return kodNegeri;
    }

    /**
     * Sets the value of the kodNegeri property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setKodNegeri(JAXBElement<String> value) {
        this.kodNegeri = value;
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
     * Gets the value of the poskod property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPoskod() {
        return poskod;
    }

    /**
     * Sets the value of the poskod property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPoskod(JAXBElement<String> value) {
        this.poskod = value;
    }

}
