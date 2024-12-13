package etanah.view;

import javax.servlet.ServletContextEvent;

import able.guice.AbleContextListener;
import able.guice.AbleModule;
import com.google.inject.Injector;

import etanah.service.session.SessionManager;
import etanah.view.etanahActionBeanContext;
import etanah.view.etanahStripesModule;
import etanah.view.etanahModule;
import net.sourceforge.stripes.action.ActionBeanContext;

public class etanahContextListener extends AbleContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        super.contextInitialized(arg0);
        
        SessionManager sm = SessionManager.getInstance();
        sm.clear();
    }

    protected void afterGuiceInit(Injector injector) {
    }

    protected AbleModule getAbleModule() {
        return new etanahModule();
    }

    protected Class<? extends ActionBeanContext> getActionBeanContextType() {
        return etanahActionBeanContext.class;  
    }
}
