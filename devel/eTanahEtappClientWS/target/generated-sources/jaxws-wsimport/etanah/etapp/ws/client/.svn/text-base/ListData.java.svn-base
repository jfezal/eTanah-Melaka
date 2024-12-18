
package etanah.etapp.ws.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for listData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="listData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listHakmilik" type="{http://ws.myetapp/}hakmilik" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="listDokumen" type="{http://ws.myetapp/}dokumen" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listData", propOrder = {
    "listHakmilik",
    "listDokumen"
})
public class ListData {

    @XmlElement(nillable = true)
    protected List<Hakmilik> listHakmilik;
    @XmlElement(nillable = true)
    protected List<Dokumen> listDokumen;

    /**
     * Gets the value of the listHakmilik property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listHakmilik property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListHakmilik().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Hakmilik }
     * 
     * 
     */
    public List<Hakmilik> getListHakmilik() {
        if (listHakmilik == null) {
            listHakmilik = new ArrayList<Hakmilik>();
        }
        return this.listHakmilik;
    }

    /**
     * Gets the value of the listDokumen property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listDokumen property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListDokumen().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Dokumen }
     * 
     * 
     */
    public List<Dokumen> getListDokumen() {
        if (listDokumen == null) {
            listDokumen = new ArrayList<Dokumen>();
        }
        return this.listDokumen;
    }

}
