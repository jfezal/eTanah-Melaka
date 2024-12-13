
package etanah.etapp.ws.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dokumen complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dokumen">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="jenisDokumen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jenisMime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="namaDokumen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="doContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dokumen", propOrder = {
    "jenisDokumen",
    "jenisMime",
    "namaDokumen",
    "doContent"
})
public class Dokumen {

    protected String jenisDokumen;
    protected String jenisMime;
    protected String namaDokumen;
    protected String doContent;

    /**
     * Gets the value of the jenisDokumen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJenisDokumen() {
        return jenisDokumen;
    }

    /**
     * Sets the value of the jenisDokumen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJenisDokumen(String value) {
        this.jenisDokumen = value;
    }

    /**
     * Gets the value of the jenisMime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJenisMime() {
        return jenisMime;
    }

    /**
     * Sets the value of the jenisMime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJenisMime(String value) {
        this.jenisMime = value;
    }

    /**
     * Gets the value of the namaDokumen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamaDokumen() {
        return namaDokumen;
    }

    /**
     * Sets the value of the namaDokumen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamaDokumen(String value) {
        this.namaDokumen = value;
    }

    /**
     * Gets the value of the doContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDoContent() {
        return doContent;
    }

    /**
     * Sets the value of the doContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDoContent(String value) {
        this.doContent = value;
    }

}
