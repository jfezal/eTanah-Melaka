
package etanah.view.portal.service.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSenaraiDokumen complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSenaraiDokumen">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SenaraiDokumen" type="{http://ws.service.portal.view.etanah}SenaraiDokumen" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSenaraiDokumen", propOrder = {
    "senaraiDokumen"
})
public class ArrayOfSenaraiDokumen {

    @XmlElement(name = "SenaraiDokumen", nillable = true)
    protected List<SenaraiDokumen> senaraiDokumen;

    /**
     * Gets the value of the senaraiDokumen property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the senaraiDokumen property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSenaraiDokumen().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SenaraiDokumen }
     * 
     * 
     */
    public List<SenaraiDokumen> getSenaraiDokumen() {
        if (senaraiDokumen == null) {
            senaraiDokumen = new ArrayList<SenaraiDokumen>();
        }
        return this.senaraiDokumen;
    }

}
