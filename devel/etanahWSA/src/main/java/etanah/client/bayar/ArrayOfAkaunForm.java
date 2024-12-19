
package etanah.client.bayar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfAkaunForm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfAkaunForm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AkaunForm" type="{http://ws.portal.view.etanah}AkaunForm" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfAkaunForm", namespace = "http://ws.portal.view.etanah", propOrder = {
    "akaunForm"
})
public class ArrayOfAkaunForm {

    @XmlElement(name = "AkaunForm", nillable = true)
    protected List<AkaunForm> akaunForm;

    /**
     * Gets the value of the akaunForm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the akaunForm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAkaunForm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AkaunForm }
     * 
     * 
     */
    public List<AkaunForm> getAkaunForm() {
        if (akaunForm == null) {
            akaunForm = new ArrayList<AkaunForm>();
        }
        return this.akaunForm;
    }

}
