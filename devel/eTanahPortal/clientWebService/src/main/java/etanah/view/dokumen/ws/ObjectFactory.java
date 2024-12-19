
package etanah.view.dokumen.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the etanah.view.dokumen.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DokumenInfoDocType_QNAME = new QName("http://ws.dokumen.view.etanah", "docType");
    private final static QName _DokumenInfoBytes_QNAME = new QName("http://ws.dokumen.view.etanah", "bytes");
    private final static QName _DokumenInfoFileName_QNAME = new QName("http://ws.dokumen.view.etanah", "fileName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: etanah.view.dokumen.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DokumenInfo }
     * 
     */
    public DokumenInfo createDokumenInfo() {
        return new DokumenInfo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dokumen.view.etanah", name = "docType", scope = DokumenInfo.class)
    public JAXBElement<String> createDokumenInfoDocType(String value) {
        return new JAXBElement<String>(_DokumenInfoDocType_QNAME, String.class, DokumenInfo.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dokumen.view.etanah", name = "bytes", scope = DokumenInfo.class)
    public JAXBElement<byte[]> createDokumenInfoBytes(byte[] value) {
        return new JAXBElement<byte[]>(_DokumenInfoBytes_QNAME, byte[].class, DokumenInfo.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.dokumen.view.etanah", name = "fileName", scope = DokumenInfo.class)
    public JAXBElement<String> createDokumenInfoFileName(String value) {
        return new JAXBElement<String>(_DokumenInfoFileName_QNAME, String.class, DokumenInfo.class, value);
    }

}
