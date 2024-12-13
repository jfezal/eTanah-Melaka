
package etanah.view.portal.service.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfStatusSemakanAkaun complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfStatusSemakanAkaun">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StatusSemakanAkaun" type="{http://ws.service.portal.view.etanah}StatusSemakanAkaun" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfStatusSemakanAkaun", propOrder = {
    "statusSemakanAkaun"
})
public class ArrayOfStatusSemakanAkaun {

    @XmlElement(name = "StatusSemakanAkaun", nillable = true)
    protected List<StatusSemakanAkaun> statusSemakanAkaun;

    /**
     * Gets the value of the statusSemakanAkaun property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statusSemakanAkaun property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatusSemakanAkaun().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StatusSemakanAkaun }
     * 
     * 
     */
    public List<StatusSemakanAkaun> getStatusSemakanAkaun() {
        if (statusSemakanAkaun == null) {
            statusSemakanAkaun = new ArrayList<StatusSemakanAkaun>();
        }
        return this.statusSemakanAkaun;
    }

}
