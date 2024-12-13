/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etanah;

import com.google.inject.Singleton;
import java.util.Properties;

/**
 *
 * @author mohd.faidzal
 */
@Singleton
public class IspeksConfig {

    public static final String CONF_FILE = "/ispeks.properties";
    private Properties props = new Properties();

    public IspeksConfig() {
        try {
            props.load(Configuration.class.getResourceAsStream(CONF_FILE));
        } catch (Exception e) {
            throw new RuntimeException("SEVERE: Could not load configuration properties at "
                    + CONF_FILE);
        }
    }

    public String getProperty(String propName) {
        if (props == null) {
            throw new RuntimeException("SEVERY: Configuration was not successful loaded!");
        }
        return props.getProperty(propName);
    }

}
