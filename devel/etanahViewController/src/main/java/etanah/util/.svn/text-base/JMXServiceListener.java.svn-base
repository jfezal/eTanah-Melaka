package etanah.util;

import com.google.inject.Injector;
import etanah.Configuration;
import etanah.view.etanahContextListener;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

public class JMXServiceListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if (WLUtil.isRunningOnWebLogic()) {
            discoverServerAddress();
        }
        server = ManagementFactory.getPlatformMBeanServer();
        load();
    }
    private static final String CONFIG_FILE = "/etanah-jmx.properties";
    private static final Logger LOG = Logger.getLogger(JMXServiceListener.class);
    private MBeanServer server;

    /**
     * Load custom JMX
     */
    private void load() {
        Properties prop = new Properties();
        try {
            prop.load(JMXServiceListener.class.getResourceAsStream(CONFIG_FILE));
            Enumeration e = prop.elements();
            while (e.hasMoreElements()) {
                try {
                    String val = (String) e.nextElement();
                    LOG.info("Registering JMX for " + val);
                    StringTokenizer token = new StringTokenizer(val, ";");
                    String typ = token.nextToken();
                    String cls = token.nextToken();
                    Class clz = Class.forName(cls);
                    Injector injector = etanahContextListener.getInjector();
                    Object obj = injector.getInstance(clz);
                    ObjectName on = new ObjectName(typ);
                    server.registerMBean(obj, on);
                } catch (Exception err) {
                    LOG.warn("Invalid JMX configuration", err);
                }
            }
        } catch (IOException ioe) {
            LOG.error("Error while loading configuration file", ioe);
        }
    }

    /**
     * Find info about server capability, if running on WebLogic
     */
    private void discoverServerAddress() {
        String defaultUrl = null;
        try {
            ObjectName service;
            service = new ObjectName("com.bea:Name=RuntimeService,Type=weblogic.management.mbeanservers.runtime.RuntimeServiceMBean");

            InitialContext ctx = new InitialContext();
            MBeanServer srv = (MBeanServer) ctx.lookup("java:comp/env/jmx/runtime");
            ObjectName rt = (ObjectName) srv.getAttribute(service, "ServerRuntime");
            defaultUrl = (String) srv.getAttribute(rt, "DefaultURL");
            ctx.close();
            
            LOG.info("Running on WebLogic Server, DefaultURL " + defaultUrl);
            Injector injector = etanahContextListener.getInjector();
            Configuration config = injector.getInstance(etanah.Configuration.class);
            config.setProperty("server.local.t3", defaultUrl);
            
        } catch (Exception e) {
            LOG.error("Unable to discover server address", e);
        }
    }
}
