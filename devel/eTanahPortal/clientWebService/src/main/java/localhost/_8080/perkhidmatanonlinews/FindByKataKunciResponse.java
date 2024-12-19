
package localhost._8080.perkhidmatanonlinews;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import etanah.view.portal.service.ws.ArrayOfSenaraiUrusan;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="out" type="{http://ws.service.portal.view.etanah}ArrayOfSenaraiUrusan"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "out"
})
@XmlRootElement(name = "findByKataKunciResponse")
public class FindByKataKunciResponse {

    @XmlElement(required = true, nillable = true)
    protected ArrayOfSenaraiUrusan out;

    /**
     * Gets the value of the out property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSenaraiUrusan }
     *     
     */
    public ArrayOfSenaraiUrusan getOut() {
        return out;
    }

    /**
     * Sets the value of the out property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSenaraiUrusan }
     *     
     */
    public void setOut(ArrayOfSenaraiUrusan value) {
        this.out = value;
    }

}
