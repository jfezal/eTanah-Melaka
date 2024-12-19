package etanah;

import java.util.Properties;

import com.google.inject.Singleton;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class Configuration implements Serializable, ConfigurationMBean {

	public static final String CONF_FILE =  "/etanah2.properties";
	
    private Properties props = new Properties();
	
	public Configuration(){
	    try {
	        props.load(Configuration.class.getResourceAsStream(CONF_FILE));
                System.setProperty("etanah/kodNegeri", props.getProperty("kodNegeri"));
                System.setProperty("etanah/coherence", props.getProperty("coherence", "true"));
                System.setProperty("etanah/dms", props.getProperty("document.path"));
	    } catch (Exception e) {
	    	props = null;
	    	throw new RuntimeException("SEVERE: Could not load configuration properties at " + 
	    			CONF_FILE);
	    }
	}
	
	public String getProperty(String propName){
		if (props == null){
			throw new RuntimeException("SEVERE: Configuration was not successful loaded!");
		}
		
		return props.getProperty(propName);
	}

        public Map<String,String> getAllPropertiesWithValue() {            
            Map<String, String> map = new HashMap<String, String>();
            Set<String> s = props.stringPropertyNames();
            
            for (Iterator<String> it = s.iterator(); it.hasNext();) {
                String string = it.next();                
                String value = getProperty(string);               
                map.put(string, value);                
            }
            return map;
        }

        public void store(Properties prop) {
            try {
                prop.store(new FileOutputStream(new File(CONF_FILE)), null);
            } catch (Exception ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /**
         * Use to override server properties typically by JMX or other runtime configurator
         */
        public void setProperty(String key, String value) {
            props.setProperty(key, value);
        }
        
        
    // ************** JMX
        
    @Override
    public String getDocumentPath() {
        return props.getProperty("document.path");
    }

    @Override
    public String getGISInboundPath() {
        return props.getProperty("gis.inbound.path");
    }

    @Override
    public String getGISOutboundPath() {
        return props.getProperty("gis.outbound.path");
    }

    @Override
    public String getJTeknikalInboundPath() {
        return props.getProperty("jteknikal.inbound.path");
    }

    @Override
    public String getKodNegeri() {
        return props.getProperty("kodNegeri");
    }

    @Override
    public String getPelanPath() {
        return props.getProperty("pelan.path");
    }

    @Override
    public String getPrintMethod() {
        return props.getProperty("print.method");
    }

    @Override
    public String getVersion() {
        return props.getProperty("version");
    }

    @Override
    public String getWORMPath() {
        return props.getProperty("worm.path");
    }

    @Override
    public boolean isCoherenceEnabled() {
        if ("true".equalsIgnoreCase(props.getProperty("coherence"))) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isSigningRequired() {
        if ("true".equalsIgnoreCase(props.getProperty("signed.required"))) {
            return true;
        }
        return false;
    }

    @Override
    public void setCoherenceEnabled(boolean state) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setDocumentPath(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setGISInboundPath(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setGISOutboundPath(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setJTeknikalInboundPath(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setKodNegeri(String kod) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPelanPath(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPrintMethod(String method) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setSigningRequired(boolean state) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setVersion(String version) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setWORMPath(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getKutipanAgensiPath() {
        return props.getProperty("kutipan.inbound.path");
    }

    @Override
    public void setKutipanAgensiPath(String path) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
	
        
}
