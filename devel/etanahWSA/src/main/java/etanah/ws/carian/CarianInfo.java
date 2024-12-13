
package etanah.client.carian;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CarianInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CarianInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="akaunBatal" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="alamat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bakiAkaun" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="bandarpekanmukim" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="icPembayar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="icPemilik" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idHakmilik" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jenisHakmilik" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kodCaw" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kodCawValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kodDaerah" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="namaPembayar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="namaPemilik" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noAkaun" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noBangunan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noHakmilik" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noPetak" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noTingkat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CarianInfo", namespace = "http://ws.portal.view.etanah", propOrder = {
    "akaunBatal",
    "alamat",
    "bakiAkaun",
    "bandarpekanmukim",
    "icPembayar",
    "icPemilik",
    "idHakmilik",
    "jenisHakmilik",
    "kodCaw",
    "kodCawValue",
    "kodDaerah",
    "namaPembayar",
    "namaPemilik",
    "noAkaun",
    "noBangunan",
    "noHakmilik",
    "noPetak",
    "noTingkat"
})
public class CarianInfo {

    @XmlElementRef(name = "akaunBatal", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<Boolean> akaunBatal;
    @XmlElementRef(name = "alamat", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> alamat;
    @XmlElementRef(name = "bakiAkaun", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<BigDecimal> bakiAkaun;
    @XmlElementRef(name = "bandarpekanmukim", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> bandarpekanmukim;
    @XmlElementRef(name = "icPembayar", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> icPembayar;
    @XmlElementRef(name = "icPemilik", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> icPemilik;
    @XmlElementRef(name = "idHakmilik", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> idHakmilik;
    @XmlElementRef(name = "jenisHakmilik", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> jenisHakmilik;
    @XmlElementRef(name = "kodCaw", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> kodCaw;
    @XmlElementRef(name = "kodCawValue", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> kodCawValue;
    @XmlElementRef(name = "kodDaerah", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> kodDaerah;
    @XmlElementRef(name = "namaPembayar", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> namaPembayar;
    @XmlElementRef(name = "namaPemilik", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> namaPemilik;
    @XmlElementRef(name = "noAkaun", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> noAkaun;
    @XmlElementRef(name = "noBangunan", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> noBangunan;
    @XmlElementRef(name = "noHakmilik", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> noHakmilik;
    @XmlElementRef(name = "noPetak", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> noPetak;
    @XmlElementRef(name = "noTingkat", namespace = "http://ws.portal.view.etanah", type = JAXBElement.class)
    protected JAXBElement<String> noTingkat;

    /**
     * Gets the value of the akaunBatal property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getAkaunBatal() {
        return akaunBatal;
    }

    /**
     * Sets the value of the akaunBatal property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setAkaunBatal(JAXBElement<Boolean> value) {
        this.akaunBatal = ((JAXBElement<Boolean> ) value);
    }

    /**
     * Gets the value of the alamat property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAlamat() {
        return alamat;
    }

    /**
     * Sets the value of the alamat property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAlamat(JAXBElement<String> value) {
        this.alamat = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the bakiAkaun property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getBakiAkaun() {
        return bakiAkaun;
    }

    /**
     * Sets the value of the bakiAkaun property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setBakiAkaun(JAXBElement<BigDecimal> value) {
        this.bakiAkaun = ((JAXBElement<BigDecimal> ) value);
    }

    /**
     * Gets the value of the bandarpekanmukim property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBandarpekanmukim() {
        return bandarpekanmukim;
    }

    /**
     * Sets the value of the bandarpekanmukim property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBandarpekanmukim(JAXBElement<String> value) {
        this.bandarpekanmukim = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the icPembayar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIcPembayar() {
        return icPembayar;
    }

    /**
     * Sets the value of the icPembayar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIcPembayar(JAXBElement<String> value) {
        this.icPembayar = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the icPemilik property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getIcPemilik() {
        return icPemilik;
    }

    /**
     * Sets the value of the icPemilik property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setIcPemilik(JAXBElement<String> value) {
        this.icPemilik = ((JAXBElement<String> ) value);
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
        this.idHakmilik = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the jenisHakmilik property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getJenisHakmilik() {
        return jenisHakmilik;
    }

    /**
     * Sets the value of the jenisHakmilik property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setJenisHakmilik(JAXBElement<String> value) {
        this.jenisHakmilik = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the kodCaw property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getKodCaw() {
        return kodCaw;
    }

    /**
     * Sets the value of the kodCaw property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setKodCaw(JAXBElement<String> value) {
        this.kodCaw = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the kodCawValue property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getKodCawValue() {
        return kodCawValue;
    }

    /**
     * Sets the value of the kodCawValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setKodCawValue(JAXBElement<String> value) {
        this.kodCawValue = ((JAXBElement<String> ) value);
    }

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
        this.kodDaerah = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the namaPembayar property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNamaPembayar() {
        return namaPembayar;
    }

    /**
     * Sets the value of the namaPembayar property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNamaPembayar(JAXBElement<String> value) {
        this.namaPembayar = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the namaPemilik property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNamaPemilik() {
        return namaPemilik;
    }

    /**
     * Sets the value of the namaPemilik property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNamaPemilik(JAXBElement<String> value) {
        this.namaPemilik = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the noAkaun property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNoAkaun() {
        return noAkaun;
    }

    /**
     * Sets the value of the noAkaun property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNoAkaun(JAXBElement<String> value) {
        this.noAkaun = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the noBangunan property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNoBangunan() {
        return noBangunan;
    }

    /**
     * Sets the value of the noBangunan property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNoBangunan(JAXBElement<String> value) {
        this.noBangunan = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the noHakmilik property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNoHakmilik() {
        return noHakmilik;
    }

    /**
     * Sets the value of the noHakmilik property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNoHakmilik(JAXBElement<String> value) {
        this.noHakmilik = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the noPetak property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNoPetak() {
        return noPetak;
    }

    /**
     * Sets the value of the noPetak property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNoPetak(JAXBElement<String> value) {
        this.noPetak = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the noTingkat property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNoTingkat() {
        return noTingkat;
    }

    /**
     * Sets the value of the noTingkat property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNoTingkat(JAXBElement<String> value) {
        this.noTingkat = ((JAXBElement<String> ) value);
    }

}
