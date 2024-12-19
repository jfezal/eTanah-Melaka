
package etanah.view.portal.service.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSenaraiBandarPekanMukim complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSenaraiBandarPekanMukim">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SenaraiBandarPekanMukim" type="{http://ws.service.portal.view.etanah}SenaraiBandarPekanMukim" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSenaraiBandarPekanMukim", propOrder = {
    "senaraiBandarPekanMukim"
})
public class ArrayOfSenaraiBandarPekanMukim {

    @XmlElement(name = "SenaraiBandarPekanMukim", nillable = true)
    protected List<SenaraiBandarPekanMukim> senaraiBandarPekanMukim;

    /**
     * Gets the value of the senaraiBandarPekanMukim property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the senaraiBandarPekanMukim property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSenaraiBandarPekanMukim().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SenaraiBandarPekanMukim }
     * 
     * 
     */
    public List<SenaraiBandarPekanMukim> getSenaraiBandarPekanMukim() {
        if (senaraiBandarPekanMukim == null) {
            senaraiBandarPekanMukim = new ArrayList<SenaraiBandarPekanMukim>();
        }
        return this.senaraiBandarPekanMukim;
    }

}
