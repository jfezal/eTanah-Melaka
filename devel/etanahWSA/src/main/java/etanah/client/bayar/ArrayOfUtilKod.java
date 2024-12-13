
package etanah.client.bayar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfUtilKod complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfUtilKod">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UtilKod" type="{http://ws.portal.view.etanah}UtilKod" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfUtilKod", namespace = "http://ws.portal.view.etanah", propOrder = {
    "utilKod"
})
public class ArrayOfUtilKod {

    @XmlElement(name = "UtilKod", nillable = true)
    protected List<UtilKod> utilKod;

    /**
     * Gets the value of the utilKod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the utilKod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUtilKod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UtilKod }
     * 
     * 
     */
    public List<UtilKod> getUtilKod() {
        if (utilKod == null) {
            utilKod = new ArrayList<UtilKod>();
        }
        return this.utilKod;
    }

}
