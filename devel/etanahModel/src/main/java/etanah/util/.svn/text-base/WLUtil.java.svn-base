package etanah.util;

import java.net.InetSocketAddress;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.naming.InitialContext;
import org.apache.log4j.Logger;
import weblogic.security.internal.SerializedSystemIni;
import weblogic.security.internal.encryption.ClearOrEncryptedService;

/**
 * <p>WebLogic Utilities</p>
 * <p>Contains helper code for various WebLogic hidden functionalities</p>
 *
 * @author minin
 */
public class WLUtil {
    private static final ClearOrEncryptedService ces;
    public static final boolean WEBLOGIC_SERVER = System.getProperty("weblogic.Name") != null;
    private static final String PREFIX = "{AES}";
    
    private WLUtil() {
        // not-instantiable
    }
   
    static {
        if (WEBLOGIC_SERVER) {
            ces = new ClearOrEncryptedService(SerializedSystemIni.getEncryptionService());        
        }
        else {
            ces = null;
        }
        
    }
    
    /*------------------------------------------------------------------------
     * Methods
     ------------------------------------------------------------------------*/
    
    /**
     * Encrypt the input string.<br/>
     * Note: The encrypted value will only works on domain where it is being generated
     * @param str Plaintext string 
     * @return 
     */
    public static String encrypt(String str) {
        String enc = "";
        if (WEBLOGIC_SERVER && ces != null) {
            enc = ces.encrypt(str);
        }
        else {
            throw new RuntimeException("This service is only available when running on WebLogic");
        }
        
        return enc;
    }
    
    /**
     * Decrypted the input string.<br/>
     * Note: The string can only be decrypted if it was encrypted on the same domain.
     * @param str Encrypted string
     * @return 
     */
    public static String decrypt(String str) {
        String dec = str;
        if (!str.startsWith(PREFIX)) {
            return str;
        }
        if (WEBLOGIC_SERVER && ces != null) {
            dec = ces.decrypt(str);
        }
        else {
            throw new RuntimeException("This service is only available when running on WebLogic");            
        }
        
        return dec;
    }
    
    /**
     * Check if we're running on WebLogic server
     * @return true if running on weblogic
     */
    public static boolean isRunningOnWebLogic() {
        return WEBLOGIC_SERVER;
    }
    
    /**
     * Get the managed server address for currently running/deployed application on weblogic.
     * 
     * Use this code instead of relying everything on the AdminServer. It should be of the same
     * environment.
     * 
     * @return the address, or null on error
     */
    public static InetSocketAddress getServerAddress() {
        InetSocketAddress address = null;
        
        // code only works on weblogic!
        if (!isRunningOnWebLogic()) 
            return null;
        
        try {
            ObjectName service = new ObjectName(
                    "com.bea:Name=RuntimeService,Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");
            
            InitialContext ctx = new InitialContext();
            MBeanServer mBeanServer = (MBeanServer) ctx.lookup("java:comp/env/jmx/runtime");
            
            ObjectName rt = (ObjectName) mBeanServer.getAttribute(service, "ServerRuntime");
            String listenAddress = (String) mBeanServer.getAttribute(rt, "ListenAddress");
            String server = listenAddress.substring(0, listenAddress.indexOf("/"));
            Integer port = (Integer) mBeanServer.getAttribute(rt, "ListenPort");
            address = new InetSocketAddress(server, port);
        } catch (Exception e) {
            Logger.getRootLogger().error("Unable to get server address", e);
        }
        return address;
        
    }
}
