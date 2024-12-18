package etanah.util;

import com.tangosol.net.CacheFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

public class CoherenceListener implements ServletContextListener {

    static final Logger log = Logger.getLogger(CoherenceListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        boolean ok = System.getProperty("etanah/kodNegeri") != null;

	if (ok && !"false".equals(System.getProperty("etanah/coherence"))) {
           log.info("Shutting down coherence");
           CacheFactory.shutdown();
	}
    }

}
