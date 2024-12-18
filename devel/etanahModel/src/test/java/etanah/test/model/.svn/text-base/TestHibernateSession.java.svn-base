package etanah.test.model;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import junit.framework.TestCase;

public class TestHibernateSession extends TestCase {
	
	/**
	 * Test the configuration of Hibernate
	 */
	public void testCreateSession(){
		Configuration cfg = new AnnotationConfiguration();
		cfg.setProperty("hibernate.hbm2ddl.auto", "validate");
		cfg.configure();
		cfg.buildSessionFactory();
	}

}
