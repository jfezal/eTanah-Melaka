
package etanah.view.portal.service.ws;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfSenaraiKodHakmilik complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfSenaraiKodHakmilik">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SenaraiKodHakmilik" type="{http://ws.service.portal.view.etanah}SenaraiKodHakmilik" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfSenaraiKodHakmilik", propOrder = {
    "senaraiKodHakmilik"
})
public class ArrayOfSenaraiKodHakmilik {

    @XmlElement(name = "SenaraiKodHakmilik", nillable = true)
    protected List<SenaraiKodHakmilik> senaraiKodHakmilik;

    /**
     * Gets the value of the senaraiKodHakmilik property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the senaraiKodHakmilik property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSenaraiKodHakmilik().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SenaraiKodHakmilik }
     * 
     * 
     */
    public List<SenaraiKodHakmilik> getSenaraiKodHakmilik() {
        if (senaraiKodHakmilik == null) {
            senaraiKodHakmilik = new ArrayList<SenaraiKodHakmilik>();
        }
        return this.senaraiKodHakmilik;
    }

}
