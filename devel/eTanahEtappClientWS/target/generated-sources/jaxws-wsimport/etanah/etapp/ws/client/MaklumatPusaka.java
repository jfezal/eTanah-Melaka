
package etanah.etapp.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for maklumatPusaka complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="maklumatPusaka">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alamat1Pemohon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alamat2Pemohon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alamat3Pemohon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bandar" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hubungan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idNegeri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="namaPemohon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="namaSimati" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noFail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noKpBaru" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noKpLain" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noKpLama" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nokpPemohon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="poskod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tarikhMati" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tarikhMohon" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "maklumatPusaka", propOrder = {
    "alamat1Pemohon",
    "alamat2Pemohon",
    "alamat3Pemohon",
    "bandar",
    "hubungan",
    "idNegeri",
    "namaPemohon",
    "namaSimati",
    "noFail",
    "noKpBaru",
    "noKpLain",
    "noKpLama",
    "nokpPemohon",
    "poskod",
    "status",
    "tarikhMati",
    "tarikhMohon"
})
public class MaklumatPusaka {

    protected String alamat1Pemohon;
    protected String alamat2Pemohon;
    protected String alamat3Pemohon;
    protected String bandar;
    protected String hubungan;
    protected String idNegeri;
    protected String namaPemohon;
    protected String namaSimati;
    protected String noFail;
    protected String noKpBaru;
    protected String noKpLain;
    protected String noKpLama;
    protected String nokpPemohon;
    protected String poskod;
    protected String status;
    protected String tarikhMati;
    protected String tarikhMohon;

    /**
     * Gets the value of the alamat1Pemohon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlamat1Pemohon() {
        return alamat1Pemohon;
    }

    /**
     * Sets the value of the alamat1Pemohon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlamat1Pemohon(String value) {
        this.alamat1Pemohon = value;
    }

    /**
     * Gets the value of the alamat2Pemohon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlamat2Pemohon() {
        return alamat2Pemohon;
    }

    /**
     * Sets the value of the alamat2Pemohon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlamat2Pemohon(String value) {
        this.alamat2Pemohon = value;
    }

    /**
     * Gets the value of the alamat3Pemohon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlamat3Pemohon() {
        return alamat3Pemohon;
    }

    /**
     * Sets the value of the alamat3Pemohon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlamat3Pemohon(String value) {
        this.alamat3Pemohon = value;
    }

    /**
     * Gets the value of the bandar property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBandar() {
        return bandar;
    }

    /**
     * Sets the value of the bandar property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBandar(String value) {
        this.bandar = value;
    }

    /**
     * Gets the value of the hubungan property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHubungan() {
        return hubungan;
    }

    /**
     * Sets the value of the hubungan property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHubungan(String value) {
        this.hubungan = value;
    }

    /**
     * Gets the value of the idNegeri property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdNegeri() {
        return idNegeri;
    }

    /**
     * Sets the value of the idNegeri property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdNegeri(String value) {
        this.idNegeri = value;
    }

    /**
     * Gets the value of the namaPemohon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamaPemohon() {
        return namaPemohon;
    }

    /**
     * Sets the value of the namaPemohon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamaPemohon(String value) {
        this.namaPemohon = value;
    }

    /**
     * Gets the value of the namaSimati property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamaSimati() {
        return namaSimati;
    }

    /**
     * Sets the value of the namaSimati property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamaSimati(String value) {
        this.namaSimati = value;
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
     * Gets the value of the noKpBaru property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoKpBaru() {
        return noKpBaru;
    }

    /**
     * Sets the value of the noKpBaru property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoKpBaru(String value) {
        this.noKpBaru = value;
    }

    /**
     * Gets the value of the noKpLain property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoKpLain() {
        return noKpLain;
    }

    /**
     * Sets the value of the noKpLain property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoKpLain(String value) {
        this.noKpLain = value;
    }

    /**
     * Gets the value of the noKpLama property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoKpLama() {
        return noKpLama;
    }

    /**
     * Sets the value of the noKpLama property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoKpLama(String value) {
        this.noKpLama = value;
    }

    /**
     * Gets the value of the nokpPemohon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNokpPemohon() {
        return nokpPemohon;
    }

    /**
     * Sets the value of the nokpPemohon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNokpPemohon(String value) {
        this.nokpPemohon = value;
    }

    /**
     * Gets the value of the poskod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoskod() {
        return poskod;
    }

    /**
     * Sets the value of the poskod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoskod(String value) {
        this.poskod = value;
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
     * Gets the value of the tarikhMati property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarikhMati() {
        return tarikhMati;
    }

    /**
     * Sets the value of the tarikhMati property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarikhMati(String value) {
        this.tarikhMati = value;
    }

    /**
     * Gets the value of the tarikhMohon property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTarikhMohon() {
        return tarikhMohon;
    }

    /**
     * Sets the value of the tarikhMohon property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTarikhMohon(String value) {
        this.tarikhMohon = value;
    }

}
