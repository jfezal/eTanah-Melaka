
package etanah.etapp.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tanah complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tanah">
 *   &lt;complexContent>
 *     &lt;extension base="{http://ws.myetapp/}updateApplicationResponse">
 *       &lt;sequence>
 *         &lt;element name="jenisHakmilik" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kegunaan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kodAgensi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kodDaerah" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kodKementerian" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kodLot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kodMukim" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kodNegeri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="luas" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="luasKeterangan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noFail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noHakmilik" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noLot" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noWarta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusHakmilik" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tarikhDaftar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UPI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tanah", propOrder = {
    "jenisHakmilik",
    "kegunaan",
    "kodAgensi",
    "kodDaerah",
    "kodKementerian",
    "kodLot",
    "kodMukim",
    "kodNegeri",
    "latitude",
    "luas",
    "luasKeterangan",
    "noFail",
    "noHakmilik",
    "noLot",
    "noWarta",
    "status",
    "statusHakmilik",
    "tarikhDaftar",
    "upi"
})
public class Tanah
    extends UpdateApplicationResponse
{

    protected String jenisHakmilik;
    protected String kegunaan;
    protected String kodAgensi;
    protected String kodDaerah;
    protected String kodKementerian;
    protected String kodLot;
    protected String kodMukim;
    protected String kodNegeri;
    protected String latitude;
    protected double luas;
    protected String luasKeterangan;
    protected String noFail;
    protected String noHakmilik;
    protected String noLot;
    protected String noWarta;
    protected String status;
    protected String statusHakmilik;
    protected String tarikhDaftar;
    @XmlElement(name = "UPI")
    protected String upi;

    /**
     * Gets the value of the jenisHakmilik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJenisHakmilik() {
        return jenisHakmilik;
    }

    /**
     * Sets the value of the jenisHakmilik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJenisHakmilik(String value) {
        this.jenisHakmilik = value;
    }

    /**
     * Gets the value of the kegunaan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKegunaan() {
        return kegunaan;
    }

    /**
     * Sets the value of the kegunaan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKegunaan(String value) {
        this.kegunaan = value;
    }

    /**
     * Gets the value of the kodAgensi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKodAgensi() {
        return kodAgensi;
    }

    /**
     * Sets the value of the kodAgensi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKodAgensi(String value) {
        this.kodAgensi = value;
    }

    /**
     * Gets the value of the kodDaerah property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKodDaerah() {
        return kodDaerah;
    }

    /**
     * Sets the value of the kodDaerah property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKodDaerah(String value) {
        this.kodDaerah = value;
    }

    /**
     * Gets the value of the kodKementerian property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKodKementerian() {
        return kodKementerian;
    }

    /**
     * Sets the value of the kodKementerian property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKodKementerian(String value) {
        this.kodKementerian = value;
    }

    /**
     * Gets the value of the kodLot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKodLot() {
        return kodLot;
    }

    /**
     * Sets the value of the kodLot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKodLot(String value) {
        this.kodLot = value;
    }

    /**
     * Gets the value of the kodMukim property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKodMukim() {
        return kodMukim;
    }

    /**
     * Sets the value of the kodMukim property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKodMukim(String value) {
        this.kodMukim = value;
    }

    /**
     * Gets the value of the kodNegeri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKodNegeri() {
        return kodNegeri;
    }

    /**
     * Sets the value of the kodNegeri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKodNegeri(String value) {
        this.kodNegeri = value;
    }

    /**
     * Gets the value of the latitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatitude(String value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the luas property.
     * 
     */
    public double getLuas() {
        return luas;
    }

    /**
     * Sets the value of the luas property.
     * 
     */
    public void setLuas(double value) {
        this.luas = value;
    }

    /**
     * Gets the value of the luasKeterangan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLuasKeterangan() {
        return luasKeterangan;
    }

    /**
     * Sets the value of the luasKeterangan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLuasKeterangan(String value) {
        this.luasKeterangan = value;
    }

    /**
     * Gets the value of the noFail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoFail() {
        return noFail;
    }

    /**
     * Sets the value of the noFail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoFail(String value) {
        this.noFail = value;
    }

    /**
     * Gets the value of the noHakmilik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoHakmilik() {
        return noHakmilik;
    }

    /**
     * Sets the value of the noHakmilik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoHakmilik(String value) {
        this.noHakmilik = value;
    }

    /**
     * Gets the value of the noLot property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoLot() {
        return noLot;
    }

    /**
     * Sets the value of the noLot property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoLot(String value) {
        this.noLot = value;
    }

    /**
     * Gets the value of the noWarta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoWarta() {
        return noWarta;
    }

    /**
     * Sets the value of the noWarta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoWarta(String value) {
        this.noWarta = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the statusHakmilik property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusHakmilik() {
        return statusHakmilik;
    }

    /**
     * Sets the value of the statusHakmilik property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusHakmilik(String value) {
        this.statusHakmilik = value;
    }

    /**
     * Gets the value of the tarikhDaftar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarikhDaftar() {
        return tarikhDaftar;
    }

    /**
     * Sets the value of the tarikhDaftar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarikhDaftar(String value) {
        this.tarikhDaftar = value;
    }

    /**
     * Gets the value of the upi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUPI() {
        return upi;
    }

    /**
     * Sets the value of the upi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUPI(String value) {
        this.upi = value;
    }

}
