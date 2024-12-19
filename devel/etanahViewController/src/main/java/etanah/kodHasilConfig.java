/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package etanah;

import java.util.Properties;

import com.google.inject.Singleton;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abu.mansur
 */
@Singleton
public class kodHasilConfig {
    public static final String CONF_FILE =  "/kodHasil.properties";
    private Properties props = new Properties();

    public kodHasilConfig(){
        try {
            props.load(Configuration.class.getResourceAsStream(CONF_FILE));
        } catch (Exception e) {
            throw new RuntimeException("SEVERE: Could not load configuration properties at " +
                            CONF_FILE);
        }
    }

    public String getProperty(String propName){
        if (props == null){
                throw new RuntimeException("SEVERY: Configuration was not successful loaded!");
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
}
