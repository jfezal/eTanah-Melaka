
package etanah.client.wslog;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the etanah.client.wslog package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: etanah.client.wslog
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InsertLog }
     * 
     */
    public InsertLog createInsertLog() {
        return new InsertLog();
    }

    /**
     * Create an instance of {@link UpdateLogResponse }
     * 
     */
    public UpdateLogResponse createUpdateLogResponse() {
        return new UpdateLogResponse();
    }

    /**
     * Create an instance of {@link UpdateLog }
     * 
     */
    public UpdateLog createUpdateLog() {
        return new UpdateLog();
    }

    /**
     * Create an instance of {@link InsertLogResponse }
     * 
     */
    public InsertLogResponse createInsertLogResponse() {
        return new InsertLogResponse();
    }

}
