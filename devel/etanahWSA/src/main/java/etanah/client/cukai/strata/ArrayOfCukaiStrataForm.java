
package etanah.client.cukai.strata;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfCukaiStrataForm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCukaiStrataForm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CukaiStrataForm" type="{http://form.ws.portal.view.etanah}CukaiStrataForm" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCukaiStrataForm", namespace = "http://form.ws.portal.view.etanah", propOrder = {
    "cukaiStrataForm"
})
public class ArrayOfCukaiStrataForm {

    @XmlElement(name = "CukaiStrataForm", nillable = true)
    protected List<CukaiStrataForm> cukaiStrataForm;

    /**
     * Gets the value of the cukaiStrataForm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cukaiStrataForm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCukaiStrataForm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CukaiStrataForm }
     * 
     * 
     */
    public List<CukaiStrataForm> getCukaiStrataForm() {
        if (cukaiStrataForm == null) {
            cukaiStrataForm = new ArrayList<CukaiStrataForm>();
        }
        return this.cukaiStrataForm;
    }

}
