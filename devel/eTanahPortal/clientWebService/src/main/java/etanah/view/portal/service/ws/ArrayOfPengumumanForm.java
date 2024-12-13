
package etanah.view.portal.service.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfPengumumanForm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfPengumumanForm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PengumumanForm" type="{http://ws.service.portal.view.etanah}PengumumanForm" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfPengumumanForm", propOrder = {
    "pengumumanForm"
})
public class ArrayOfPengumumanForm {

    @XmlElement(name = "PengumumanForm", nillable = true)
    protected List<PengumumanForm> pengumumanForm;

    /**
     * Gets the value of the pengumumanForm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pengumumanForm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPengumumanForm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PengumumanForm }
     * 
     * 
     */
    public List<PengumumanForm> getPengumumanForm() {
        if (pengumumanForm == null) {
            pengumumanForm = new ArrayList<PengumumanForm>();
        }
        return this.pengumumanForm;
    }

}
