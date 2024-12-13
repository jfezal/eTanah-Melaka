
package etanah.client.bayar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSejarahCukai complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSejarahCukai">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SejarahCukai" type="{http://ws.portal.view.etanah}SejarahCukai" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSejarahCukai", namespace = "http://ws.portal.view.etanah", propOrder = {
    "sejarahCukai"
})
public class ArrayOfSejarahCukai {

    @XmlElement(name = "SejarahCukai", nillable = true)
    protected List<SejarahCukai> sejarahCukai;

    /**
     * Gets the value of the sejarahCukai property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sejarahCukai property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSejarahCukai().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SejarahCukai }
     * 
     * 
     */
    public List<SejarahCukai> getSejarahCukai() {
        if (sejarahCukai == null) {
            sejarahCukai = new ArrayList<SejarahCukai>();
        }
        return this.sejarahCukai;
    }

}
