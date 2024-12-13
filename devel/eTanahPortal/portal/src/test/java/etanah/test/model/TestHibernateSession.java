package etanah.test.model;

import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import junit.framework.TestCase;

public class TestHibernateSession extends TestCase {

    private Properties user;

    /**
     * Test the configuration of Hibernate
     */
    public void testCreateSession() {
        AnnotationConfiguration cfg = new AnnotationConfiguration();
        cfg.setProperty("hibernate.hbm2ddl.auto", "validate");
        cfg.configure();

        Properties config = cfg.getProperties();
        // we need to remove reference to datasource
        config.remove("hibernate.connection.datasource");
        config.remove("hibernate.jndi.url");
        config.remove("hibernate.jndi.class");
        // remove cache entries
        config.remove("hibernate.cache.use_structured_entries");
        config.remove("hibernate.cache.provider_class");
        cfg.setProperties(config);

        cfg.setProperty("hibernate.connection.url", user.getProperty("jdbc.url"));
        cfg.setProperty("hibernate.connection.username", user.getProperty("jdbc.user"));
        cfg.setProperty("hibernate.connection.password", user.getProperty("jdbc.pass"));
        // disable cache for test
        cfg.setProperty("hibernate.cache.use_second_level_cache", "false");
        cfg.setProperty("hibernate.cache.use_query_cache", "false");
        System.out.println("-----------------");
        print(cfg.getProperties());
        System.out.println("-----------------");
//        System.out.println("Logging is set to WARNING, change this to INFO if you want verbose output.");
        cfg.buildSessionFactory();
    }

    @Override
    public void setUp() {
	// maybe not so gud idea after all
//        Logger.getAnonymousLogger().getParent().setLevel(Level.WARNING);
        try {
            System.out.println("Loading properties...");
            user = new Properties();
            user.load(able.stripes.StripesModule.class.getResourceAsStream("/database.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void print(Properties p) {
        Enumeration e = p.keys();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            if (key.indexOf("hibernate") != -1) {
                System.out.println(key + ": " + p.getProperty(key));
            }
        }
    }
}
